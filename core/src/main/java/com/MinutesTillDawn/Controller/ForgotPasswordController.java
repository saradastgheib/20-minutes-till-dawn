package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.User;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.View.ForgotPasswordMenu;
import com.MinutesTillDawn.View.LoginMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ForgotPasswordController {
    private static ForgotPasswordController controller;
    private ForgotPasswordMenu view;
    public ForgotPasswordController() {
        controller = this;
    }

    public void  setView (ForgotPasswordMenu menu ) {
        this.view = menu;
        this.addListeners();
    }

    private void addListeners() {
        view.resetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = view.usernameField.getText();
                String password = view.usernameField.getText();
                User user = UserDatabase.getDatabase().getUser(username);
                if (user == null ) {
                    view.error.setColor(Color.RED);
                    view.error.setText("User doesn't exist");
                }
                // TODO validate password
                else {
                    UserDatabase.getDatabase().updatePassword(user, password);
                    view.error.setText("Teeeheee");
                }

            }
        });
        view.backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(LoginMenu.getLoginMenu());
            }
        });
    }
    public static ForgotPasswordController getController() {
        if (controller != null) return controller;
        return new ForgotPasswordController();
    }
}
