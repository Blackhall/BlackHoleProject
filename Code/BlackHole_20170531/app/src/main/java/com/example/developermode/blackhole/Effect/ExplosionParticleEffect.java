package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Float2;

import com.example.developermode.blackhole.GameFramework.PhysicalCollision.CollisionTool;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DeveloperMode on 2017-05-27.
 */

public class ExplosionParticleEffect extends Effect {
    ArrayList<Particle> m_ParticleList = null;
    float fResistivity = 1800.0f;

    public ExplosionParticleEffect(float fX, float fY, float fExplosionPower, int iParticleSize) {
        m_fX = fX;
        m_fY = fY;
        m_fLifeTime = 0.9f;
        m_ParticleList = new ArrayList<Particle>();
        float fLengthVelocity = 0.0f;
        for(int i = 0; i < 80; i++) {
            fLengthVelocity = (new Random().nextInt() % (fExplosionPower + 900 - (fExplosionPower + 400) + 1) + fExplosionPower + 400);
            Particle particle = new BubbleParticle(
                    m_fX, m_fY,
                    (float)(fLengthVelocity*(Math.cos((2.0 * 3.14 / 360.0) * (360.0 / 40.0 * (double)i)))),
                    (float)(fLengthVelocity*(Math.sin((2.0 * 3.14 / 360.0) * (360.0 / 40.0 * (double)i)))),
                    new Random().nextInt() % (iParticleSize + 60 - iParticleSize + 1) + iParticleSize,
                    new Random().nextInt() % (iParticleSize + 60 - iParticleSize + 1) + iParticleSize);
            m_ParticleList.add(particle);
        }
    }

    public void Update(float fElapsedTime) {
        Float2 f2CounterVelocity = new Float2();
        float fLengthVelocity = 0.0f;
        float fDeceleration = 0.0f;
        float fPostVX = 0.0f;
        float fPostVY = 0.0f;

        for(int i = 0; i < 80; i++) {
            Particle particle = m_ParticleList.get(i);
            f2CounterVelocity.x = -particle.m_fVX;
            f2CounterVelocity.y = -particle.m_fVY;
            fLengthVelocity = CollisionTool.GetDistance(
                    f2CounterVelocity,
                    new Float2(0.0f, 0.0f));
            CollisionTool.Float2Normalize(f2CounterVelocity, f2CounterVelocity);
            fDeceleration = fResistivity * fElapsedTime;
            if(fDeceleration > fLengthVelocity) fDeceleration = fLengthVelocity;
            f2CounterVelocity.x = f2CounterVelocity.x * fDeceleration;
            f2CounterVelocity.y = f2CounterVelocity.y * fDeceleration;
            fPostVX = particle.m_fVX + f2CounterVelocity.x;
            fPostVY = particle.m_fVY + f2CounterVelocity.y;

            particle.m_fX = particle.m_fX + (particle.m_fVX + fPostVX) / 2.0f *fElapsedTime;
            particle.m_fY = particle.m_fY + (particle.m_fVY + fPostVY) / 2.0f *fElapsedTime;

            particle.m_fVX = fPostVX;
            particle.m_fVY = fPostVY;
        }
    }

    public void Render(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        for(int i = 0; i < 80; i++) {
            p.setAlpha((int)(255 * m_fLifeTime / 0.9f));
            Particle particle = m_ParticleList.get(i);
            canvas.drawCircle(particle.m_fX, particle.m_fY, particle.GetRadius() * m_fLifeTime / 0.9f, p);
        }
    }
}
