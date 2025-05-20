package com.MinutesTillDawn.Model;

import com.MinutesTillDawn.Model.User;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Array;

public class UserDatabase {
    private static UserDatabase database;
    private Array<User> users = new Array<>();
    private final String FILE_NAME = "users.json";
    private Json json = new Json();
    private Player currentPlayer;

    public UserDatabase() {
        load();
    }

    public static UserDatabase getDatabase () {
        if (database ==null)database = new UserDatabase();
        return database;
    }


    private void load() {
        FileHandle file = Gdx.files.local(FILE_NAME);
        if (file.exists()) {
            users = json.fromJson(Array.class, User.class, file.readString());
        }
    }

    public void save() {
        FileHandle file = Gdx.files.local(FILE_NAME);
        file.writeString(json.toJson(users), false);
    }

    public void register(String username, String password, String avatar, String securityQuestion, String securityAnswer) {
        users.add(new User(username, password, avatar,securityQuestion, securityAnswer));
        save();
    }

    public boolean userExists(String username) {
        return getUser(username) != null;
    }
    public boolean validate(String username, String password) {
        User u = getUser(username);
        return u != null && u.getPassword().equals(password);
    }

    public boolean passwordIsStrong(String password) {
        if (password.length() < 8) return false;

        String SPECIAL_CHARS = "@_()*&%$#";
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (SPECIAL_CHARS.indexOf(c) >= 0) hasSpecial = true;

            if (hasUpper && hasDigit && hasSpecial) return true;
        }

        return false;
    }

    public User getUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }
    public void updatePassword (User user, String newPassword) {
        user.setPassword(newPassword);
        save();
    }

    public void setCurrentUser(Player currentUser) {
        this.currentPlayer = currentUser;
    }

    public Player getCurrentUser() {
        if (currentPlayer == null) {
            currentPlayer = getGuestPlayer();
        }
        return currentPlayer;
    }

    public Player getGuestPlayer() {
        return new Player(new User("Guest" + MathUtils.random(1000, 9999), null, GameAssetManager.getGameAssetManager().getRandomAvatarPath(), null, null), true);
    }
}
