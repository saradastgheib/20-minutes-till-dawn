package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.User;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.View.ChangeUsername;
import com.MinutesTillDawn.View.ProfileMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ChangeUsernameController {

    private ChangeUsername view;

    public void setView(ChangeUsername view) {
        this.view = view;
        addListeners();
    }

    public void addListeners() {
        view.changeUsernameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = view.usernameField.getText();

                User user = UserDatabase.getDatabase().getCurrentUser().getUser();
                if (user == null ) {
                    view.error.setText("something went wrong");
                }
                else {
                    UserDatabase.getDatabase().updateUsername(user, username);
                    view.error.setColor(92f / 255f, 116f/255f, 92f/255f, 1);
                    view.error.setText("username changed successfully");
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
