package com.MinutesTillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Array;


public class GameSettings {
    public static int gameTime = 5;
    static Array<Music> musics = new Array<>();
    static int[] currentTrackIndex = {0};
    public static boolean sfxEnabled = true, autoReloadEnabled;
    public static String controlScheme;
   static  {
        musics.add(Gdx.audio.newMusic(Gdx.files.internal("music/disasterpeace.mp3")));
       musics.add(Gdx.audio.newMusic(Gdx.files.internal("music/lazerhawk.mp3")));
       musics.add(Gdx.audio.newMusic(Gdx.files.internal("music/turboKiller.mp3")));
    }
    public static Music getCurrentMusic() {
       return musics.get(currentTrackIndex[0]);
    }
}
