package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;

/**
 * Created by DeveloperMode on 2017-05-30.
 */

public class BubbleParticle extends Particle {
    private float m_fRadius;
    private float m_fMass;

    public BubbleParticle(float fX, float fY, float fVX, float fVY, float fRadius, float fMass) {
        m_fX = fX;
        m_fY = fY;
        m_fVX = fVX;
        m_fVY = fVY;
        m_fRadius = fRadius;
        m_fMass = fMass;
    }

    public float GetRadius() {
        return m_fRadius;
    }

    public void Update(float fElapsedTime) {
    }

    public void Render(Canvas canvas) {

    }
}
