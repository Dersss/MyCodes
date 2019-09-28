import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel00 extends JPanel {
    Timer t = new Timer(1, new Listener());
    int ctr = 0;
    double G = 0.1; //Gravitational Constant

    final int xpos = 280;

    double[] p2d = { 280, 200 };
    double[] v2d = { 0, 0 };

    int points = 0;
    int lives = 0;
    int sides = 13;

    double snorm = 400;
    double sd = 450;
    double sv = 0;
    boolean setlock = false;
    boolean rdown, ldown;
    double paddle = 130;
    double rtheta = 0;
    double ltheta = 0;

    int preset[][] = { { 0, 400, 135, 450, 1 }, //right paddle
            { 135, 450, 270, 400, 1 }, //left paddle
            { 270, 0, 300, 20, 1 }, //first bouncey thingy
            { 291, 0, 291, 500, 1 }, //right wall
            { -1, 0, 270, 0, 1 }, //top wall
            { 0, -1, 0, 500, 1 } //left wall
    };

    int[][] balls = { { 80, 80, 30, 50 }, { 230, 280, 20, 200 },
            { 50, 200, 25, 100 }, { 200, 100, 10, 500 } };
    int lines[][] = new int[100][5];

    public Panel00() {
        super();
        this.t.start();
        this.addKeyListener(new Key());
        this.setFocusable(true);

        for (int i = 0; i < this.preset.length; i++) {
            this.lines[i] = this.preset[i];
        }

        int plen = this.preset.length;

        int ct = 0;
        for (int k = 0; k < this.balls.length; k++) {
            int px = this.balls[k][0], py = this.balls[k][1],
                    radius = this.balls[k][2];
            for (double i = 0; i < 2 * Math.PI; i += 2 * Math.PI / this.sides) {
                ct++;
                this.lines[plen + ct][0] = px + (int) (radius * Math.cos(i));
                this.lines[plen + ct][1] = py + (int) (radius * Math.sin(i));
                this.lines[plen + ct][2] = px + (int) (radius
                        * Math.cos(i - 2 * Math.PI / this.sides));
                this.lines[plen + ct][3] = py + (int) (radius
                        * Math.sin(i - 2 * Math.PI / this.sides));
            }
        }

    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Panel00.this.repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.v2d[1] += this.G;
        this.p2d[1] += this.v2d[1];
        this.p2d[0] += this.v2d[0];

        if (this.p2d[1] > 1000) {
            this.p2d[0] = 280;
            this.p2d[1] = 200;
            this.v2d[0] = 0;
            this.v2d[1] = 0;
            this.lives++;
        }
        if (this.p2d[0] == 280 && this.p2d[1] > this.sd) {
            this.p2d[1] = this.sd;
            this.v2d[1] = Math.min(this.v2d[1], this.sv);
        }

        if (this.setlock == false) {
            this.sv *= 0.95; //the dampening coefficient for the springiness
            this.sv -= (this.sd - this.snorm) / 30;
            this.sd += this.sv;
        }
        double rc = 0.1;
        if (this.rdown) {
            this.rtheta = Math.max(-0.5, this.rtheta - rc);
        } else {
            this.rtheta = Math.min(0.5, this.rtheta + rc);
        }
        if (this.ldown) {
            this.ltheta = Math.max(-0.5, this.ltheta - rc);
        } else {
            this.ltheta = Math.min(0.5, this.ltheta + rc);
        }

        this.lines[0][2] = this.lines[0][0]
                + (int) (Math.cos(this.ltheta) * this.paddle);
        this.lines[0][3] = this.lines[0][1]
                + (int) (Math.sin(this.ltheta) * this.paddle);
        this.lines[1][0] = this.lines[1][2]
                + (int) (-Math.cos(this.rtheta) * this.paddle);
        this.lines[1][1] = this.lines[1][3]
                + (int) (Math.sin(this.rtheta) * this.paddle);
        int rX = (int) this.p2d[0];
        int rY = (int) this.p2d[1];
        int r = 10;
        g.setColor(Color.blue);
        g.drawArc(rX - r, rY - r, 2 * r, 2 * r, 0, 360);
        g.setColor(Color.black);
        for (int i = 0; i < this.lines.length; i++) {
            int x1 = this.lines[i][0], y1 = this.lines[i][1],
                    x2 = this.lines[i][2];
            double y2 = this.lines[i][3] + 0.0001;
            if (i > this.preset.length) {
                g.setColor(Color.red);
            }
            g.drawLine(x1, y1, x2, (int) Math.round(y2));

            double bmag = Math.sqrt(
                    this.v2d[0] * this.v2d[0] + this.v2d[1] * this.v2d[1]);
            double lineslope = (x2 - x1) / (y2 - y1);
            double ballslope = this.v2d[0] / this.v2d[1];
            //System.out.println(ballslope + " " + lineslope);
            //xpos * ballslope + p2d[1] = xpos * lineslope + y1;
            double binter = this.p2d[0] - ballslope * this.p2d[1];
            double linter = x1 - lineslope * y1;

            double y = (binter - linter) / (lineslope - ballslope);
            double sx = y * ballslope + binter;
            //double qx = y * lineslope + linter;
            double la = Math.atan2(y2 - y1, x2 - x1);
            double ba = Math.atan2(this.v2d[1], this.v2d[0]);

            double da = 2 * la - ba;

            //System.out.println(sx + " " + y);
            /*
             * g.drawLine((int)sx, (int)y, (int)p2d[0], (int)p2d[1]);
             * g.fillRect((int)sx - 2, (int)y - 2, 4, 4);
             * g.drawLine((int)p2d[0], (int)p2d[1], (int) (p2d[0] + Math.cos(da)
             * * 100), (int)(p2d[1] + Math.sin(da) * 100)); //
             */
            if (sx >= Math.min(x2, x1) && sx <= Math.max(x1, x2)
                    && Math.min(y1, y2) <= y && Math.max(y1, y2) >= y) {
                double interdist = Math.sqrt(Math.pow(sx - this.p2d[0], 2)
                        + Math.pow(y - this.p2d[1], 2));
                double tiny = 0.0001;
                double futuredist = Math.sqrt(Math
                        .pow(sx - (this.p2d[0] + Math.cos(ba) * tiny), 2)
                        + Math.pow(y - (this.p2d[1] + Math.sin(ba) * tiny), 2));

                if (interdist <= bmag + r && futuredist < interdist) {
                    //System.out.println("Carl Sagan" + i); //this is a pun because he wrote a book called Contact
                    if (i > this.preset.length) {
                        int ball = (int) Math
                                .floor((i - this.preset.length) / this.sides);
                        //System.out.println(balls[ball][2]);
                        this.points += this.balls[ball][3] * bmag;
                    }
                    this.v2d[0] = Math.cos(da) * bmag;
                    this.v2d[1] = Math.sin(da) * bmag;
                }
            }
        }
        g.setColor(Color.black);
        //System.out.println(sx + " " + qx);
        //System.out.println(ballslope + " " + lineslope);
        //double slope = Math.atan2(v2d[1], v2d[0]);
        //g.drawLine((int) p2d[0], (int) p2d[1], (int) (p2d[0]+10*v2d[0]), (int) (p2d[1]+10*v2d[1]));

        g.fillRect(this.xpos - 5, (int) this.sd + 10, 10, 20);

        g.drawString("Score: " + this.points + " Resets: " + this.lives, 10,
                15);

    }

    private class Key extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                Panel00.this.setlock = true;
                Panel00.this.sd += 2;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Panel00.this.ldown = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Panel00.this.rdown = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            Panel00.this.setlock = false;
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                Panel00.this.ldown = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                Panel00.this.rdown = false;
            }
        }
    }
}
