package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.View.ChangeUsername;
import com.MinutesTillDawn.View.ProfileMenu;
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
    }
}
