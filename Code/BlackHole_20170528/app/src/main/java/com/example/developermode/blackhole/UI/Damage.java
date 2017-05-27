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
    protected float m_fFontSize;
    protected int m_iColor;
    static final float fLiftTime = 0.4f;
    static final float fGravityMagnitude = +6000.0f;

    public Damage(int iDamage, float fX, float fY, float fFontSize, int iColor) {
        m_iDamage = iDamage;
        m_fX = fX;
        m_fY = fY;
        m_fVX = new Random().nextInt() % (200 + 200 + 1) - 200;
        m_fVY = -900.0f;
        m_fLifeTime = fLiftTime;
        m_fFontSize = fFontSize;
        m_iColor = iColor;
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
        p.setTextSize(m_fFontSize * (m_fLifeTime/fLiftTime));
        p.setColor(m_iColor);
        canvas.drawText(String.valueOf(m_iDamage), m_fX, m_fY, p);
    }
}
