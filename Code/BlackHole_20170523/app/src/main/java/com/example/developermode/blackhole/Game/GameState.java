package com.example.developermode.blackhole.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Float2;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.developermode.blackhole.GameFramework.AppManager;
import com.example.developermode.blackhole.GameFramework.IState;
import com.example.developermode.blackhole.GameFramework.PhysicalCollision.CollisionTool;
import com.example.developermode.blackhole.GameFramework.Timer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DeveloperMode on 2017-05-18.
 */

public class GameState implements IState {
    private Timer m_timer;
    private BackGround m_background;
    private Blackhole m_blackhole;
    long CurrentTimeCount = System.currentTimeMillis();
    Random randTool = new Random();

    ArrayList<Planet> m_PlanetList = new ArrayList<Planet> ();

    public void MakePlanet() {
        if(m_PlanetList.size() >= 8) return;
        if(System.currentTimeMillis() - CurrentTimeCount >= 1000) {
            CurrentTimeCount = System.currentTimeMillis();

            Planet plnt = null;

            switch (randTool.nextInt(3))
            {
                case 0:
                    plnt = new SmallPlanet();
                    plnt.SetVelocity(
                            new Float2(
                                    randTool.nextInt() % 2 == 0 ? randTool.nextInt() % (200 - 100 + 1) + 100 : randTool.nextInt() % (-100 + 200 + 1) - 200,
                                    randTool.nextInt() % 2 == 0 ? randTool.nextInt() % (200 - 100 + 1) + 100 : randTool.nextInt() % (-100 + 200 + 1) - 200));
                    break;
                case 1:
                    plnt = new MiddlePlanet();
                    plnt.SetVelocity(
                            new Float2(
                                    randTool.nextInt() % 2 == 0 ? randTool.nextInt() % (150 - 50 + 1) + 50 : randTool.nextInt() % (-50 + 150 + 1) - 150,
                                    randTool.nextInt() % 2 == 0 ? randTool.nextInt() % (150 - 50 + 1) + 50 : randTool.nextInt() % (-50 + 150 + 1) - 150));
                    break;
                case 2:
                    plnt = new BigPlanet();
                    plnt.SetVelocity(
                            new Float2(
                                    randTool.nextInt() % 2 == 0 ? randTool.nextInt() % (75 - 25 + 1) + 25 : randTool.nextInt() % (-25 + 75 + 1) - 75,
                                    randTool.nextInt() % 2 == 0 ? randTool.nextInt() % (75 - 25 + 1) + 25 : randTool.nextInt() % (-25 + 75 + 1) - 75));
                    break;
            }

            plnt.SetPosition(randTool.nextInt(1440), randTool.nextInt(2560));




            m_PlanetList.add(plnt);
        }
    }
    @Override
    public void Init() {
        m_timer = new Timer();
        m_background = new BackGround();
        m_blackhole = new Blackhole();
        AppManager.getInstance().m_gameState = this;
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        m_timer.Tick();
        m_background.Update(m_timer.GetElapsedTime());
        m_blackhole.Update(m_timer.GetElapsedTime());
        for(Planet plnt : m_PlanetList) {
            //plnt.Update(m_timer.GetElapsedTime());
            m_blackhole.PullPlanet(plnt, m_timer.GetElapsedTime());
        }

        for(int i = 0; i < m_PlanetList.size(); i++) {
            for(int j = i + 1; j < m_PlanetList.size(); j++) {
                CollisionTool.ImpurseBasedCollisionResolution(m_PlanetList.get(i), m_PlanetList.get(j));
            }
        }

        MakePlanet();
    }

    @Override
    public void Render(Canvas canvas) {
        m_background.Draw(canvas);
        m_blackhole.Draw(canvas);

        for(Planet plnt : m_PlanetList) {
            plnt.Draw(canvas);

        }
        Paint p = new Paint();
        p.setTextSize(100); p.setColor(Color.WHITE);
        canvas.drawText("걸린 시간 :"+ String.valueOf(m_timer.GetElapsedTime()), 0, 100, p);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
