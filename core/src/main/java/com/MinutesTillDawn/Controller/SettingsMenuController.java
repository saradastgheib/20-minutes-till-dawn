package com.MinutesTillDawn.Controller;

public class SettingsMenuController {
    private static SettingsMenuController controller;

    public static SettingsMenuController getController() {
        if (controller == null) controller = new SettingsMenuController();
        return controller;
    }
}
