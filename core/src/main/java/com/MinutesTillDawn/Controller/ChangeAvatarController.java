package com.MinutesTillDawn.Controller;

import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.UserDatabase;
import com.MinutesTillDawn.View.ChangeAvatar;
import com.MinutesTillDawn.View.PreGameMenu;
import com.MinutesTillDawn.View.ProfileMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ChangeAvatarController {
    private ChangeAvatar view;

    public void setView(ChangeAvatar view, ImageButton[] characters) {
        this.view = view;
        addListeners(characters);
    }
    private void addListeners(ImageButton[] characters) {
        for (ImageButton btn : characters) {
            btn.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    view.largePreview.setDrawable(new TextureRegionDrawable(new Texture("characters/" + btn.getName() + "/avatar.png")));
                    UserDatabase.getDatabase().getCurrentUser().getUser().setCharacterName(btn.getName());
                    view.characterName.setText(btn.getName());
                }
            });
        }
        view.back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new ProfileMenu());
            }
        });
    }
}
