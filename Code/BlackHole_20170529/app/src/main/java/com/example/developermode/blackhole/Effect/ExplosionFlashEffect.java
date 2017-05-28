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
    static final int iAlpha = 120;

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
        p.setColor(Color.GRAY);
        p.setAlpha(m_iAlpha);
        canvas.drawRect(0, 0, 1440, 2560, p);
        p.setColor(Color.WHITE);
        p.setAlpha((int)(150 * m_fLifeTime/m_fFirstLifeTime));
        canvas.drawCircle(m_fX, m_fY, m_fRadius + 300.0f * (1.0f - m_fLifeTime/m_fFirstLifeTime), p);
        p.setAlpha((int)(200 * m_fLifeTime/m_fFirstLifeTime));
        canvas.drawCircle(m_fX, m_fY, m_fRadius + 120.0f * (1.0f - m_fLifeTime/m_fFirstLifeTime), p);
        p.setAlpha((int)(255 * m_fLifeTime/m_fFirstLifeTime));
        canvas.drawCircle(m_fX, m_fY, m_fRadius + 0.0f * (1.0f - m_fLifeTime/m_fFirstLifeTime), p);
    };
}
