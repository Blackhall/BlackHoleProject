package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.renderscript.Double2;
import android.renderscript.Float2;

import com.example.developermode.blackhole.GameFramework.PhysicalCollision.CollisionTool;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DeveloperMode on 2017-05-30.
 */

public class CollisionLineParticleEffect extends Effect {
    ArrayList<Particle> m_LineParticleList = null;
    private int m_iLineParticleNum;
    private float m_fCollisionPower;

    public CollisionLineParticleEffect(float fX, float fY, float fSlopeNormalX, float fSlopeNormalY, float fCollisionPower, float fMaxLifeTime) {
        m_fX = fX;
        m_fY = fY;
        m_fLifeTime = fMaxLifeTime;
        m_LineParticleList = new ArrayList<Particle>();
        m_fCollisionPower = fCollisionPower;
        m_iLineParticleNum = (int)fCollisionPower * 4;//new Random().nextInt() % (10 - 5 + 1) + 5;
        if(m_iLineParticleNum > 50) m_iLineParticleNum = 50;

        Float2 f2SlopeNormal = new Float2(fSlopeNormalX, fSlopeNormalY);
        CollisionTool.Float2Normalize(f2SlopeNormal, f2SlopeNormal);
        float fDot = CollisionTool.Float2Dot(new Float2(0.0f, -1.0f), f2SlopeNormal);
        if (fDot >= 1.0f) fDot = 1.0f;
        else if (fDot <= -1.0f) fDot = -1.0f;
        double dRotateRadian = Math.acos((double)fDot);

        dRotateRadian = f2SlopeNormal.x >= 0.0f ? dRotateRadian : -dRotateRadian;

        for(int i = 0; i < m_iLineParticleNum; i++) {

            double dVX = (double)(new Random().nextInt() % (70 * m_fCollisionPower + 70 * m_fCollisionPower + 1) - 70 * m_fCollisionPower);
            double dVY = (double)(new Random().nextInt() % (30 * m_fCollisionPower + 30 * m_fCollisionPower + 1) - 30 * m_fCollisionPower);

            double d2RotedVX = dVX;
            double d2RotedVY = dVY;

            d2RotedVX = dVX * (float)Math.cos(dRotateRadian) - dVY * (float)Math.sin(dRotateRadian);
            d2RotedVY = dVX * (float)Math.sin(dRotateRadian) + dVY * (float)Math.cos(dRotateRadian);

            Particle lineParticle = new LineParticle(
                    (float)(new Random().nextInt() % (70 - 50 + 1) + 50),
                    fX, fY, (float)d2RotedVX, (float)d2RotedVY,
                    (float)(new Random().nextInt() % 360),
                    (float)(new Random().nextInt() % (200 + 200 + 1) - 200),
                    m_fCollisionPower + (new Random().nextInt() % (6 - 2 + 1) + 2),
                    Color.rgb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)),
                    new Random().nextFloat() * fMaxLifeTime);
            m_LineParticleList.add(lineParticle);
        }
    }

    public void Update(float fElapsedTime) {
        for(int i = m_LineParticleList.size() - 1 ; i >= 0; i--) {
            Particle lineParticle = m_LineParticleList.get(i);
            lineParticle.Update(fElapsedTime);
            lineParticle.m_fLifeTime -= fElapsedTime;
            if(lineParticle.m_fLifeTime <= 0) {
                m_LineParticleList.remove(i);
                //return;
            }
        }
    }

    public void Render(Canvas canvas) {
        Rect ScreenRect = new Rect(0, 0, 1440, 2560);
        for(int i = m_LineParticleList.size() - 1 ; i >= 0; i--) {
            Particle lineParticle = m_LineParticleList.get(i);
            if(!CollisionTool.IsCollided(ScreenRect, (int)lineParticle.m_fX, (int)lineParticle.m_fY)) continue;
            lineParticle.Render(canvas);
        }
    }
}
