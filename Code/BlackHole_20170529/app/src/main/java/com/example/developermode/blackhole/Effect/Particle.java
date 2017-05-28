package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Float2;

import com.example.developermode.blackhole.GameFramework.PhysicalCollision.CollisionTool;

/**
 * Created by DeveloperMode on 2017-05-27.
 */

public class Particle {
    public float m_fX;
    public float m_fY;
    public float m_fVX;
    public float m_fVY;
    public float m_fRadius;
    public float m_fMass;

    public Particle(float fX, float fY, float fVX, float fVY, float fRadius, float fMass) {
        m_fX = fX;
        m_fY = fY;
        m_fVX = fVX;
        m_fVY = fVY;
        m_fRadius = fRadius;
        m_fMass = fMass;
    }

    public void Update(float fElapsedTime) {
    }

    public void Render(Canvas canvas) {

    }
}
