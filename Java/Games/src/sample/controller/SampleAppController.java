package sample.controller;

import sample.view.SampleFrame;

public class SampleAppController {

    private SampleFrame appFrame;

    public void start() {
        this.appFrame = new SampleFrame(this);
    }
}
