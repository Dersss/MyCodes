package sample.model;

import java.awt.Color;
import java.awt.Graphics;

public class Ship {

    // define the shape of the ship and its flame
    final double[] origXPts = { 14, -10, -6, -10 }, origYPts = { 0, -8, 0, 8 },
            origFlameXPts = { -6, -23, -6 }, origFlameYPts = { -3, 0, 3 };
    final int radius = 6; // radius of circle used to approximate the ship

    double x, y, angle, xVelocity, yVelocity, acceleration, velocityDecay,
            rotationalSpeed; //variables used in movement
    boolean turningLeft, turningRight, accelerating, active;
    int[] xPts, yPts, flameXPts, flameYPts; //store the current locations
    //of the points used to draw the ship and its flame
    int shotDelay, shotDelayLeft; //used to determine the rate of firing

    public Ship(double x, double y, double angle, double acceleration,
            double velocityDecay, double rotationalSpeed, int shotDelay) {
        // this.x refers to the Ship's x, x refers to the x parameter
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.acceleration = acceleration;
        this.velocityDecay = velocityDecay;
        this.rotationalSpeed = rotationalSpeed;
        this.xVelocity = 0; // not moving
        this.yVelocity = 0;
        this.turningLeft = false; // not turning
        this.turningRight = false;
        this.accelerating = false; // not accelerating
        this.active = false; // start off paused
        this.xPts = new int[4]; // allocate space for the arrays
        this.yPts = new int[4];
        this.flameXPts = new int[3];
        this.flameYPts = new int[3];
        this.shotDelay = shotDelay; // # of frames between shots
        this.shotDelayLeft = 0; // ready to shoot
    }

    public void draw(Graphics g) {
        //rotate the points, translate them to the ship's location (by

        //adding x and y), then round them by adding .5 and casting them
        //as integers (which truncates any decimal place)
        if (this.accelerating && this.active) { // draw flame if accelerating
            for (int i = 0; i < 3; i++) {
                this.flameXPts[i] = (int) (this.origFlameXPts[i]
                        * Math.cos(this.angle)
                        - this.origFlameYPts[i] * Math.sin(this.angle) + this.x
                        + .5);
                this.flameYPts[i] = (int) (this.origFlameXPts[i]
                        * Math.sin(this.angle)
                        + this.origFlameYPts[i] * Math.cos(this.angle) + this.y
                        + .5);
            }
            g.setColor(Color.red); //set color of flame
            g.fillPolygon(this.flameXPts, this.flameYPts, 3); // 3 is # of points
        }
        //calculate the polygon for the ship, then draw it
        for (int i = 0; i < 4; i++) {
            this.xPts[i] = (int) (this.origXPts[i] * Math.cos(this.angle) - //rotate
                    this.origYPts[i] * Math.sin(this.angle) + this.x + .5); //translate and round
            this.yPts[i] = (int) (this.origXPts[i] * Math.sin(this.angle) + //rotate
                    this.origYPts[i] * Math.cos(this.angle) + this.y + .5); //translate and round
        }
        if (this.active) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.darkGray);
        }
        g.fillPolygon(this.xPts, this.yPts, 4); // 4 is the number of points
    }

    public void move(int scrnWidth, int scrnHeight) {
        if (this.shotDelayLeft > 0) {
            this.shotDelayLeft--; //is run, so this ticks down the shot delay
        }
        if (this.turningLeft) {
            this.angle -= this.rotationalSpeed; //because positive y is downward.
        }
        if (this.turningRight) {
            this.angle += this.rotationalSpeed; //rotating clockwise (to the right)
        }
        if (this.angle > (2 * Math.PI)) {
            this.angle -= (2 * Math.PI);
        } else if (this.angle < 0) {
            this.angle += (2 * Math.PI);
        }
        if (this.accelerating) { //adds accel to velocity in direction pointed
            //calculates components of accel and adds them to velocity
            this.xVelocity += this.acceleration * Math.cos(this.angle);
            this.yVelocity += this.acceleration * Math.sin(this.angle);
        }
        this.x += this.xVelocity; //move the ship by adding velocity to position
        this.y += this.yVelocity;
        this.xVelocity *= this.velocityDecay; //slows ship down by percentages (velDecay
        this.yVelocity *= this.velocityDecay; //should be a decimal between 0 and 1
        if (this.x < 0) {
            this.x += scrnWidth; //when it goes out of the screen's bounds
        } else if (this.x > scrnWidth) {
            this.x -= scrnWidth;
        }
        if (this.y < 0) {
            this.y += scrnHeight;
        } else if (this.y > scrnHeight) {
            this.y -= scrnHeight;
        }
    }

    public void setAccelerating(boolean accelerating) {
        this.accelerating = accelerating; //start or stop accelerating the ship
    }

    public void setTurningLeft(boolean turningLeft) {
        this.turningLeft = turningLeft; //start or stop turning the ship
    }

    public void setTurningRight(boolean turningRight) {
        this.turningRight = turningRight;
    }

    public double getX() {
        return this.x; // returns the ship’s x location
    }

    public double getY() {
        return this.y;
    }

    public double getRadius() {
        return this.radius; // returns radius of circle that approximates the ship
    }

    public void setActive(boolean active) {
        this.active = active; //used when the game is paused or unpaused
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean canShoot() {
        if (this.shotDelayLeft > 0) {
            return false; //shoot again yet or if it needs to wait longer
        } else {
            return true;
        }
    }
}
