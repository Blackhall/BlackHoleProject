package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by DeveloperMode on 2017-05-29.
 */

public class ExplosionFlashEffect extends Effect {
    private int m_iAlpha;
    private float m_fRadius;
    //static final float fLifeTime = 0.9f;
    private float m_fFirstLifeTime;
    static final int iAlpha = 160;

    public  ExplosionFlashEffect(float fX, float fY, float fRadius, float fLifeTime) {
        m_fX = fX;
        m_fY = fY;
        m_fRadius = fRadius;
        m_fFirstLifeTime = fLifeTime;
        m_fLifeTime = fLifeTime;
    }

    public void Init() {};
    public void Destroy() {};
    public void Update(float fElapsedTime) {
        m_iAlpha = (int)(iAlpha * m_fLifeTime/m_fFirstLifeTime);
    };
    public void Render(Canvas canvas) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        //p.setAlpha(m_iAlpha);
        p.setColor(Color.BLACK);
        p.setAlpha(m_iAlpha);
        canvas.drawRect(0, 0, 1440, 2560, p);
        p.setColor(Color.WHITE);
        p.setAlpha((int)(30 * m_fLifeTime/m_fFirstLifeTime));
        canvas.drawCircle(m_fX, m_fY, (m_fRadius + m_fRadius * 300.0f/100.0f) * m_fLifeTime/m_fFirstLifeTime
                + (m_fRadius + m_fRadius *350.0f/100.0f) * (1.0f - m_fLifeTime/m_fFirstLifeTime), p);
        p.setAlpha((int)(60 * m_fLifeTime/m_fFirstLifeTime));
        canvas.drawCircle(m_fX, m_fY, (m_fRadius + m_fRadius * 180.0f/100.0f) * m_fLifeTime/m_fFirstLifeTime
                + (m_fRadius + m_fRadius *310.0f/100.0f) * (1.0f - m_fLifeTime/m_fFirstLifeTime), p);
        p.setAlpha((int)(180 * m_fLifeTime/m_fFirstLifeTime));
        canvas.drawCircle(m_fX, m_fY, (m_fRadius + m_fRadius * 100.0f/100.0f) * m_fLifeTime/m_fFirstLifeTime
                + (m_fRadius + m_fRadius * 120.0f/100.0f) * (1.0f - m_fLifeTime/m_fFirstLifeTime), p);
        p.setAlpha((int)(210 * m_fLifeTime/m_fFirstLifeTime));
        canvas.drawCircle(m_fX, m_fY, (m_fRadius + m_fRadius * 40.0f/100.0f) * m_fLifeTime/m_fFirstLifeTime
                + (m_fRadius + m_fRadius * 45.0f/100.0f) * (1.0f - m_fLifeTime/m_fFirstLifeTime), p);
    };
}
