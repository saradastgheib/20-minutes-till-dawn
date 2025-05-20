package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.Player;
import com.MinutesTillDawn.Model.User;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.View.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Timer;
import java.util.TimerTask;

public class LoginMenuController {
    private LoginMenu view;
    private static LoginMenuController controller;

    public LoginMenuController() {
        controller = this;
    }
    public static LoginMenuController getController () {
        if (controller != null) return  controller;
        return  new LoginMenuController();
    }

    public void setView(LoginMenu view) {
        this.view = view;
        addListeners();
    }
    private void addListeners() {
        {
            view.loginButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    try {
                        String username = view.usernameField.getText();
                        String password = view.passwordField.getText();
                        User user = UserDatabase.getDatabase().getUser(username);
                        if (user == null) {
                            view.error.setColor(Color.RED);
                            view.error.setText("❌ User does not exist.");
                        } else if (!UserDatabase.getDatabase().validate(username, password)) {
                            view.error.setColor(Color.RED);
                            view.error.setText("❌ Incorrect password.");
                        } else {
                            view.error.setColor(Color.GREEN);
                            view.error.setText("✅ Login successful!");
                            UserDatabase.getDatabase().setCurrentUser(new Player(user, false));
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Gdx.app.postRunnable(() -> {
                                        Main.getMain().setScreen(new MainMenu(MainMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin()));
                                    });
                                }
                            }, 1);
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            });

            view.forgotPasswordButton.addListener(new

                                                      ClickListener() {
                                                          @Override
                                                          public void clicked(InputEvent event, float x, float y) {
                                                              Main.getMain().setScreen(ForgotPasswordMenu.getForgotPasswordMenu());
                                                          }
                                                      });

            view.registerButton.addListener(new

                                                ClickListener() {
                                                    @Override
                                                    public void clicked(InputEvent event, float x, float y) {
                                                        Main.getMain().setScreen(RegisterMenu.getRegisterMenu());
                                                    }
                                                });
        }
    }
    }


