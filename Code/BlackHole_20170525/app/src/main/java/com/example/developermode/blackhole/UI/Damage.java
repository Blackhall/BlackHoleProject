package com.example.developermode.blackhole.UI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by DeveloperMode on 2017-05-25.
 */

public class Damage {
    protected int m_iDamage;
    protected float m_fX;
    protected float m_fY;
    protected float m_fVX;
    protected float m_fVY;
    protected float m_fLifeTime;
    static final float fLiftTime = 0.4f;
    static final float fGravityMagnitude = +6000.0f;

    public Damage(int iDamage, float fX, float fY) {
        m_iDamage = iDamage;
        m_fX = fX;
        m_fY = fY;
        m_fVX = 1000.0f;
        m_fVY = -900.0f;
        m_fLifeTime = fLiftTime;
    }

    public void Update(float fElapsedTime) {
        float postVX = m_fVX;
        float postVY = m_fVY + fGravityMagnitude * fElapsedTime;

        m_fX = m_fX + (m_fVX + postVX) * fElapsedTime / 2.0f;
        m_fY = m_fY + (m_fVY + postVY) * fElapsedTime / 2.0f;

        m_fVX = postVX;
        m_fVY = postVY;
    }

    public void Render(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(270 * (m_fLifeTime/fLiftTime));
        p.setColor(Color.RED);
        canvas.drawText(String.valueOf(m_iDamage), m_fX, m_fY, p);
    }
}
