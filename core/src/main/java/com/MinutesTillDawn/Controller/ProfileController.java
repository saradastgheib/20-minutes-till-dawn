package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.User;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.View.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ProfileController {
    private ProfileMenu view;
    public void  setView (ProfileMenu profileMenu) {
        view = profileMenu;
        addListeners();
    }
    public void addListeners() {
        view.changeUsername.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new ChangeUsername());
            }
        });
        view.changePassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new ChangePassword());
            }
        });
        view.changeAvatar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new ChangeAvatar(new ChangeAvatarController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
        view.back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new ChangeMenu(new ChangeMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
    }

    public void deleteAccount() {
        User user = UserDatabase.getDatabase().getUser(UserDatabase.getDatabase().getCurrentUser().getUsername());
        UserDatabase.getDatabase().removeUser(user);
        Main.getMain().setScreen(new LoginMenu(LoginMenuController.getController(), GameAssetManager.getGameAssetManager().getSkin()));
    }
}
