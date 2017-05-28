package com.example.developermode.blackhole.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Float2;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.developermode.blackhole.Effect.EffectManager;
import com.example.developermode.blackhole.Effect.ExplosionFlashEffect;
import com.example.developermode.blackhole.Effect.ExplosionParticleEffect;
import com.example.developermode.blackhole.Effect.TwinklingPlanetEffect;
import com.example.developermode.blackhole.Effect.WaveEffect;
import com.example.developermode.blackhole.GameFramework.AppManager;
import com.example.developermode.blackhole.GameFramework.IState;
import com.example.developermode.blackhole.GameFramework.PhysicalCollision.CollisionTool;
import com.example.developermode.blackhole.GameFramework.Timer;
import com.example.developermode.blackhole.UI.Damage;
import com.example.developermode.blackhole.UI.DamageManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DeveloperMode on 2017-05-18.
 */

public class GameState implements IState {
    private Timer m_timer;
    private DamageManager m_DamageManager;
    private EffectManager m_EffectManager;
    private BackGround m_background;
    private Blackhole m_blackhole;
    static boolean bIsDrag = false;
    long CurrentTimeCount = System.currentTimeMillis();
    Random randTool = new Random();
    private static final int ClickDamage = 250;
    private static final int BoomDamage = 130;

    ArrayList<Planet> m_PlanetList = new ArrayList<Planet> ();

    public void MakePlanet() {
        if(m_PlanetList.size() >= 20) return;
        if(System.currentTimeMillis() - CurrentTimeCount >= 600) {
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

            int iRandDegree = randTool.nextInt(360);
            plnt.SetPosition(
                    (int)(1500.0 * Math.cos((2.0 * 3.14) / 360.0 * (float)iRandDegree ) + 1500),
                    (int)(1500.0 * Math.sin((2.0 * 3.14) / 360.0 * (float)iRandDegree) + 1500));

            //randTool.nextInt(1440)
            //randTool.nextInt(2560)

            m_PlanetList.add(plnt);
        }
    }

    public void AttackPlanet(Planet plnt, int iClickedX, int iClickedY) {
        //static int iMinAssignedNum = 0;
        if(plnt.m_iAssignedNum == 0) return;

        if(CollisionTool.IsCollided(plnt, iClickedX, iClickedY)) {

        }
    }
    @Override
    public void Init() {
        m_timer = new Timer();
        m_background = new BackGround();
        m_blackhole = new Blackhole();
        m_DamageManager = new DamageManager();
        m_EffectManager = new EffectManager();
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

        for(int i = m_PlanetList.size() - 1; i >= 0; i--) {
            Planet plnt = m_PlanetList.get(i);

            if(plnt.m_iHP <= 0) {
                int iDamage = 0;
                for(int j = m_PlanetList.size() - 1; j >= 0; j--) {
                    Planet expPlnt = m_PlanetList.get(j);
                    if(expPlnt.equals(plnt)) continue;
                    if(CollisionTool.IsCollided(
                            plnt.GetX(),plnt.GetY(), plnt.m_fRadius * 250.0f / 100.0f,
                            expPlnt.GetX(), expPlnt.GetY(), expPlnt.m_fRadius)) {
                        int iColorR = randTool.nextInt() % 256;
                        int iColorG = randTool.nextInt() % 100;
                        int iColorB = randTool.nextInt() % 100;
                        m_EffectManager.RegisterEffectObj(new TwinklingPlanetEffect(
                                expPlnt,
                                expPlnt.m_fRadius,
                                Color.rgb(255, iColorG, iColorB),
                                Color.rgb(255, iColorG, iColorB),
                                plnt.m_fRadius/320.0f));
                        float fInfluence = 1.0f - (
                                CollisionTool.GetDistance(
                                        new Float2(plnt.GetX(),plnt.GetY()),
                                        new Float2(expPlnt.GetX(), expPlnt.GetY()))
                                        - (plnt.m_fRadius + expPlnt.m_fRadius))
                                /(plnt.m_fRadius * 250.0f / 100.0f);
                        iDamage = (int)(BoomDamage * fInfluence + BoomDamage * fInfluence * randTool.nextInt(21) / 100);
                        expPlnt.m_iHP -= iDamage;
                        Float2 f2ExplosionVelocity = new Float2(expPlnt.GetX() - plnt.GetX(), expPlnt.GetY() - plnt.GetY());
                        CollisionTool.Float2Normalize(f2ExplosionVelocity, f2ExplosionVelocity);
                        expPlnt.m_f2Velocity.x += f2ExplosionVelocity.x * plnt.m_fRadius * 1.8f * fInfluence;
                        expPlnt.m_f2Velocity.y += f2ExplosionVelocity.y * plnt.m_fRadius * 1.8f * fInfluence;
                        m_DamageManager.RegisterDamageObj(new Damage(
                                iDamage,
                                expPlnt.GetX() + (randTool.nextInt() % (10 + 10 + 1) - 10) * 3,
                                expPlnt.GetY() + (randTool.nextInt() % (10 + 10 + 1) - 10) * 3,
                                400 * fInfluence,
                                Color.rgb(230 , 100 , 80)));
                    }
                }
                m_EffectManager.RegisterEffectObj(new TwinklingPlanetEffect(
                        plnt,
                        plnt.m_fRadius,
                        Color.rgb(0, 0, 0),
                        Color.rgb(10, 10, 10),
                        0.5f));
                m_EffectManager.RegisterEffectObj(new WaveEffect(
                        plnt.GetX(), plnt.GetY(),
                        plnt.m_fRadius, plnt.m_fRadius + plnt.m_fRadius * 250.0f / 100.0f,
                        Color.rgb(0, 0, 255),
                        Color.rgb(255, 255, 255),
                        plnt.m_fRadius/320.0f
                ));
                m_EffectManager.RegisterEffectObj(new WaveEffect(
                        plnt.GetX(), plnt.GetY(),
                        plnt.m_fRadius, plnt.m_fRadius + plnt.m_fRadius * 180.0f / 100.0f,
                        Color.rgb(0, 0, 255),
                        Color.rgb(255, 255, 255),
                        plnt.m_fRadius/320.0f
                ));
                m_EffectManager.RegisterEffectObj(new ExplosionParticleEffect(plnt.GetX(), plnt.GetY(), plnt.m_fRadius * 3, (int)(plnt.m_fRadius / 8.0f)));
                m_EffectManager.RegisterEffectObj(new ExplosionFlashEffect(plnt.GetX(), plnt.GetY(), plnt.m_fRadius, 0.45f));
                m_PlanetList.remove(i);
            }
            else {
                plnt.Update(m_timer.GetElapsedTime());
                m_blackhole.PullPlanet(plnt, m_timer.GetElapsedTime());
                m_blackhole.AssignNumToPlanet(m_PlanetList, plnt);
            }
        }

        for(int i = 0; i < m_PlanetList.size(); i++) {
            for(int j = i + 1; j < m_PlanetList.size(); j++) {
                CollisionTool.ImpurseBasedCollisionResolution(m_PlanetList.get(i), m_PlanetList.get(j));
            }
        }
        m_EffectManager.Update(m_timer.GetElapsedTime());
        m_DamageManager.Update(m_timer.GetElapsedTime());
        MakePlanet();
    }

    @Override
    public void Render(Canvas canvas) {
        m_background.Draw(canvas);
        m_blackhole.Draw(canvas);

        float fDistanceFromBlackhole = Float.MAX_VALUE;
        Float2 f2BlackholePos = new Float2(m_blackhole.GetX(), m_blackhole.GetY());
        Float2 f2PlanetPos = new Float2();
        Planet NearestPlanet = null;
        Planet SecondNearestPlanet = null;
        float fDistance = 0.0f;

        for(int i = m_PlanetList.size() - 1; i >= 0; i--) {
            Planet plnt = m_PlanetList.get(i);
            plnt.Draw(canvas);

            f2PlanetPos.x = plnt.GetX();
            f2PlanetPos.y = plnt.GetY();
            fDistance = CollisionTool.GetDistance(f2BlackholePos, f2PlanetPos);
            if(fDistanceFromBlackhole > fDistance) {
                fDistanceFromBlackhole = fDistance;
                NearestPlanet = plnt;
            }
        }

        fDistanceFromBlackhole = Float.MAX_VALUE;

        for(int i = m_PlanetList.size() - 1; i >= 0; i--) {
            Planet plnt = m_PlanetList.get(i);
            if(plnt.equals(NearestPlanet)) continue;
            f2PlanetPos.x = plnt.GetX();
            f2PlanetPos.y = plnt.GetY();
            fDistance = CollisionTool.GetDistance(f2BlackholePos, f2PlanetPos);
            if(fDistanceFromBlackhole > fDistance) {
                fDistanceFromBlackhole = fDistance;
                SecondNearestPlanet = plnt;
            }
        }

        if(NearestPlanet != null) {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setStrokeWidth(20);
            float fDistanceFromNPToBH = 0.0f;
            float fDistanceFromSNPToBH = 0.0f;
            float fInterpToN = 0.0f;
            if(SecondNearestPlanet != null) {

                //p.setStrokeWidth(10);
                fDistanceFromNPToBH = CollisionTool.GetDistance(
                        new Float2(NearestPlanet.GetX(), NearestPlanet.GetY()),
                        new Float2(m_blackhole.GetX(), m_blackhole.GetY()));
                fDistanceFromSNPToBH = CollisionTool.GetDistance(
                        new Float2(SecondNearestPlanet.GetX(), SecondNearestPlanet.GetY()),
                        new Float2(m_blackhole.GetX(), m_blackhole.GetY()));
                fInterpToN = 1.0f - fDistanceFromNPToBH / (fDistanceFromNPToBH + fDistanceFromSNPToBH);
                p.setColor(Color.rgb((int)(255 * (1.0f - fInterpToN)), (int)(255 * fInterpToN), 255));
                p.setAlpha((int)(255 * (1.0f - fInterpToN)));
                canvas.drawCircle(SecondNearestPlanet.GetX(), SecondNearestPlanet.GetY(), SecondNearestPlanet.m_fRadius, p);
            }
            p.setColor(Color.rgb((int)(255 * (fInterpToN)), (int)(255 * (1.0f - fInterpToN)), 255));
            p.setAlpha((int)(255 * (fInterpToN)));
            canvas.drawCircle(NearestPlanet.GetX(), NearestPlanet.GetY(), NearestPlanet.m_fRadius, p);
        }

        m_EffectManager.Render(canvas);
        m_DamageManager.Render(canvas);

        Paint p = new Paint();
        //Paint p = new Paint();
        p.setTextSize(105);
        p.setColor(Color.WHITE);
        if(m_PlanetList.size() > 0) {
            canvas.drawText("m_fMass :"+ String.valueOf(m_PlanetList.get(0).m_fMass), 0, 105, p);
            canvas.drawText("m_fRadius :"+ String.valueOf(m_PlanetList.get(0).m_fRadius), 0, 105 * 2, p);
            canvas.drawText("m_fVelocity.x :"+ String.valueOf(m_PlanetList.get(0).m_f2Velocity.x), 0, 105 * 3, p);
            canvas.drawText("m_fVelocity.y :"+ String.valueOf(m_PlanetList.get(0).m_f2Velocity.y), 0, 105 * 4, p);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                int px = (int) event.getX();
                int py = (int) event.getY();

                for(int i = m_PlanetList.size() - 1; i >= 0; i--) {
                    Planet plnt = m_PlanetList.get(i);
                    //if(plnt.m_iAssignedNum == 0) continue;
                    if(CollisionTool.IsCollided(plnt, px, py)) {
                        m_EffectManager.RegisterEffectObj(new TwinklingPlanetEffect(
                                plnt,
                                0.0f,
                                Color.rgb(255, 255, 0),
                                Color.rgb(255, 255, 0),
                                0.24f));
                        m_EffectManager.RegisterEffectObj(new WaveEffect(
                                (float)px, (float)py,
                                20.0f, 250.0f,
                                Color.rgb(255, 0, 0),
                                Color.rgb(200, 55, 0),
                                0.9f
                        ));
                        float fDistance = CollisionTool.GetDistance(
                                new Float2((float)px, (float)py),
                                new Float2(plnt.GetX(), plnt.GetY()));
                        int iDamage = 0;
                        if(35.0f > fDistance/plnt.m_fRadius * 100.0f) {
                            iDamage = (ClickDamage + 200) + (ClickDamage + 200) * randTool.nextInt(21) / 100;
                            m_DamageManager.RegisterDamageObj(new Damage(
                                    iDamage,
                                    px + (randTool.nextInt() % (10 + 10 + 1) - 10) * 3,
                                    py + (randTool.nextInt() % (10 + 10 + 1) - 10) * 3,
                                    350,
                                    Color.rgb(255, 0, 0)));
                        }
                        else if(65.0f > fDistance/plnt.m_fRadius * 100.0f) {
                            iDamage = ClickDamage + ClickDamage * randTool.nextInt(21) / 100;
                            m_DamageManager.RegisterDamageObj(new Damage(
                                    iDamage,
                                    px + (randTool.nextInt() % (10 + 10 + 1) - 10) * 3,
                                    py + (randTool.nextInt() % (10 + 10 + 1) - 10) * 3,
                                    250,
                                    Color.rgb(230, 100, 30)));
                        }
                        else {
                            iDamage = (ClickDamage - 200) + (ClickDamage - 200) * randTool.nextInt(21) / 100;
                            m_DamageManager.RegisterDamageObj(new Damage(
                                    iDamage,
                                    px + (randTool.nextInt() % (10 + 10 + 1) - 10) * 3,
                                    py + (randTool.nextInt() % (10 + 10 + 1) - 10) * 3,
                                    200,
                                    Color.rgb(200, 200, 60)));
                        }
                        plnt.m_iHP -= iDamage;

                        return true;
                    }
                }
                break;

        }

        return true;
    }
}
