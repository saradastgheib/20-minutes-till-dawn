package com.MinutesTillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Array;


public class GameSettings {
    public static int gameTime = 5;
    public static Array<Music> musics = new Array<>();
    public static int[] currentTrackIndex = {0};
    public static boolean sfxEnabled = true, autoReloadEnabled = true;
    public static String controlScheme = "ARROWS", language = "english";
   static  {
        musics.add(Gdx.audio.newMusic(Gdx.files.internal("music/disasterpeace.mp3")));
       musics.add(Gdx.audio.newMusic(Gdx.files.internal("music/lazerhawk.mp3")));
       musics.add(Gdx.audio.newMusic(Gdx.files.internal("music/turboKiller.mp3")));
    }
    public static Music getCurrentMusic() {
       return musics.get(currentTrackIndex[0]);
    }
    public static void setVolume(float volume) {
       for (Music music : musics) {
           music.setVolume(volume);
       }
    }
}
