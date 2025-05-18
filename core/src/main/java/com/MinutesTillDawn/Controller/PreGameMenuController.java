package com.MinutesTillDawn.Controller;

public class PreGameMenuController {
    private static PreGameMenuController controller;
    public PreGameMenuController() {
        controller = this;
    }

    public static PreGameMenuController getController() {
        if (controller != null) return  controller;
        return new PreGameMenuController();
    }
}
