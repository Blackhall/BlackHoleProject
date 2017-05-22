package com.example.developermode.blackhole.GameFramework;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.developermode.blackhole.GameFramework.GameView;

/**
 * Created by DeveloperMode on 2017-05-18.
 */

public class GameViewThread extends Thread {
    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameview;

    private boolean m_run = false;

    public GameViewThread(SurfaceHolder m_surfaceHolder, GameView gameView) {
        this.m_surfaceHolder = m_surfaceHolder;
        m_gameview = gameView;
    }

    public void setRunning(boolean run) {
        m_run = run;
    }

    @Override
    public void run() {
        super.run();

        Canvas _canvas;
        while(m_run) {
            _canvas = null;
            try {
                m_gameview.Update();
                _canvas = m_surfaceHolder.lockCanvas(null);
                synchronized (m_surfaceHolder) {
                    m_gameview.MyOnDraw(_canvas);
                }
            } finally {
                if(_canvas != null)
                    m_surfaceHolder.unlockCanvasAndPost(_canvas);
            }
        }
    }
}
