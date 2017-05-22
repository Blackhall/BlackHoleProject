package com.example.developermode.blackhole.GameFramework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.developermode.blackhole.Game.GameState;

/**
 * Created by DeveloperMode on 2017-05-18.
 */

public class AppManager {
    private static AppManager s_instance;
    private GameView m_gameview;
    private Resources m_resources;
    public GameState m_gameState;

    public static AppManager getInstance(){
        if(s_instance == null){
            s_instance = new AppManager();
        }
        return s_instance;
    }

    void setGameView(GameView _gameview) {
        m_gameview = _gameview;
    }

    void setResources(Resources _resources) {
        m_resources = _resources;
    }

    public GameView getGameView() {
        return m_gameview;
    }

    public Resources getResources() {
        return m_resources;
    }

    public Bitmap getBitmap(int r) {
        return BitmapFactory.decodeResource(m_resources, r);
    }
}
