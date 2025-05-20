package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.Player;
import com.MinutesTillDawn.Model.User;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.View.LoginMenu;
import com.MinutesTillDawn.View.MainMenu;
import com.MinutesTillDawn.View.PreGameMenu;
import com.MinutesTillDawn.View.RegisterMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterMenuController {
    private static RegisterMenuController registerMenuController;
    private RegisterMenu view;
    public RegisterMenuController() {
        registerMenuController = this;
    }

    public void setView(RegisterMenu view) {
        this.view = view;
        this.addListeners();
    }

    private void addListeners() {
        view.registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = view.usernameField.getText();
                String password = view.passwordField.getText();
                String securityAnswer = view.securityQuestionField.getText();
                String securityQuestion = view.securityQuestionBox.getSelected();

                if (UserDatabase.getDatabase().userExists(username)){
                    view.message.setColor(Color.RED);
                    view.message.setText("Username taken");
                }

                else if (!UserDatabase.getDatabase().passwordIsStrong(password)) {
                    view.message.setColor(Color.RED);
                    view.message.setText("Weak password.");
                }
                else {
                        UserDatabase.getDatabase().register(username, password, GameAssetManager.getGameAssetManager().getRandomAvatarPath(), securityQuestion, securityAnswer);
                        view.message.setColor(Color.GREEN);
                        view.message.setText("Registered successfully");

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Gdx.app.postRunnable(() -> {
                                    Main.getMain().setScreen(new LoginMenu(LoginMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin()));
                                });
                            }
                        }, 1);
                }

            }
        });
        view.guestButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                UserDatabase.getDatabase().setCurrentUser(UserDatabase.getDatabase().getGuestPlayer());
                Main.getMain().setScreen(MainMenu.getMainMenu());
            }
        });

        view.backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(LoginMenu.getLoginMenu());
            }
        });
    }
    public static RegisterMenuController getController() {
        if (registerMenuController == null) return new RegisterMenuController();
        return registerMenuController;
    }
}
