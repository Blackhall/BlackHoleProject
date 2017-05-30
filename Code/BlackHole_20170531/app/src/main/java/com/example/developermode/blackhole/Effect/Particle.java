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
    public float m_fInitialLifeTime;
    public float m_fLifeTime;

    public Particle() {
        m_fX = 0.0f;
        m_fY = 0.0f;
        m_fVX = 0.0f;
        m_fVY = 0.0f;
        m_fInitialLifeTime = 0.0f;
        m_fLifeTime = 0.0f;
    }

    public float GetRadius() { return 0.0f; }

    public void Update(float fElapsedTime) {
    }

    public void Render(Canvas canvas) {

    }
}
