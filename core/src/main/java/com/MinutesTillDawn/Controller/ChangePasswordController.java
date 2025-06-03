package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.User;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.View.ChangePassword;
import com.MinutesTillDawn.View.ProfileMenu;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ChangePasswordController {
    ChangePassword view;
    public void setView(ChangePassword view) {
        this.view = view;
        addListeners();
    }
    public void addListeners() {
        view.resetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String newPassword = view.passwordField.getText();
                User user = UserDatabase.getDatabase().getCurrentUser().getUser();
                if (user == null ) {
                    view.error.setText("something went wrong");
                }
                else {
                    UserDatabase.getDatabase().updatePassword(user, newPassword);
                    view.error.setColor(92f / 255f, 116f/255f, 92f/255f, 1);
                    view.error.setText("password changed successfully");
                }
            }
        });

        view.backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new ProfileMenu());
            }
        });
    }
}
