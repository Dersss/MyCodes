package sample.view;

import javax.swing.JPanel;

import sample.controller.SampleAppController;

public class SamplePanel extends JPanel {

    private SampleAppController baseController;

    public SamplePanel(SampleAppController baseController) {
        this.baseController = baseController;
    }
}
