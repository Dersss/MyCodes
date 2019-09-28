package sample.view;

import javax.swing.JFrame;

import sample.controller.SampleAppController;
import sample.model.Ship;

public class SampleFrame extends JFrame {
    private SamplePanel basePanel;
    Ship ship;
    boolean paused;

    public SampleFrame(SampleAppController baseController) {
        this.basePanel = new SamplePanel(baseController);
        this.setupFrame();
    }

    private void setupFrame() {
        this.setContentPane(this.basePanel);
        this.setSize(500, 500);
        this.setVisible(true);
        this.ship = new Ship(250, 250, 0, .35, .98, .1, 12);
        this.ship.isActive();
    }

}
