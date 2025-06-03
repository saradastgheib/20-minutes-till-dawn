package com.MinutesTillDawn.View;

import com.MinutesTillDawn.Controller.ProfileController;
import com.MinutesTillDawn.Main;
import com.MinutesTillDawn.Model.Enums.Label;
import com.MinutesTillDawn.Model.GameAssetManager;
import com.MinutesTillDawn.Model.GameSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.badlogic.gdx.utils.JsonValue.ValueType.object;

public class ProfileMenu implements Screen {
    private Stage stage;
    private ProfileController controller = new ProfileController();
    Skin skin = GameAssetManager.getGameAssetManager().getSkin();
    public TextButton changeUsername, changePassword, deleteAccountButton;

    public ProfileMenu() {
        changePassword = new TextButton(Label.CHANGEPASSWORD.getText(), skin);
        changeUsername = new TextButton(Label.CHANGEUSERNAME.getText(), skin);
        deleteAccountButton = new TextButton(Label.DELETEACCOUNT.getText(), skin);
        controller.setView(this);
    }

    @Override
    public void show() {
       try {
            stage = new Stage(new ScreenViewport());
            Gdx.input.setInputProcessor(stage);

            Table table = new Table();
            table.setFillParent(true);
            table.center();
            stage.addActor(table);

            setButton(changeUsername);
            table.add(changeUsername).padBottom(50);

            table.row();
            setButton(changePassword);
            table.add(changePassword).padBottom(50);

            table.row();
            setButton(deleteAccountButton);
            table.add(deleteAccountButton).padBottom(50);
            deleteAccountButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Dialog confirmDialog = new Dialog("", skin) {
                        protected void result(Object object) {
                            boolean confirmed = (Boolean) object;
                            if (confirmed) {
                                controller.deleteAccount();
                            }
                        }
                    };

                    TextButton yesButton = new TextButton("yes", skin);
                    setButton(yesButton);
                    TextButton noButton = new TextButton("no", skin);
                    setButton(noButton);
                    confirmDialog.text("Are you sure you want to delete your account?");
                    confirmDialog.button(yesButton, true);
                    confirmDialog.button(noButton, false);
                    confirmDialog.setModal(true);
                    confirmDialog.setMovable(false);
                    confirmDialog.setColor(13f / 255f, 18f / 255f, 37f / 255f, 1f);
                    confirmDialog.show(stage);

                }

                private void setButton(TextButton button) {
                    button.setColor(new Color(13f / 255f, 18f / 255f, 37f / 255f, 255f / 255f));
                    button.getStyle().fontColor = Color.WHITE;
                    button.getStyle().overFontColor = new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
                }


            });
        }
       catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }

    @Override
    public void render(float v) {

        ScreenUtils.clear(new Color(13f / 255f, 18f / 255f, 37f / 255f, 1f));

        Batch batch = Main.getBatch();
        if (GameAssetManager.getGameAssetManager().bwEnabled) {
            batch.setShader(Main.getMain().grayscaleShader);
        } else {
            batch.setShader(null);
        }

        Main.getBatch().begin();
        Main.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    private void setButton(TextButton button) {
        button.setColor(new Color(13f / 255f, 18f / 255f, 37f / 255f, 255f / 255f));
        button.getStyle().fontColor = Color.WHITE;
        button.getStyle().overFontColor = new Color(253f / 255f, 81f / 255f, 97f / 255f, 1f);
    }
}
