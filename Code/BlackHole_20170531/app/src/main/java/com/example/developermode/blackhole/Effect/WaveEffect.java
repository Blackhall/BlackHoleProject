package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.developermode.blackhole.Game.Planet;

/**
 * Created by DeveloperMode on 2017-05-28.
 */

public class WaveEffect extends Effect {
    private int m_iAlpha;
    private float m_fX;
    private float m_fY;
    private float m_fFirstRadius;
    private float m_fLastRadius;
    private int m_iFirstColor;
    private int m_iLastColor;
    //static final float fLifeTime = 0.9f;
    private float m_fFirstLifeTime;
    static final int iAlpha = 255;

    public WaveEffect(float fX, float fY, float fFirstRadius, float fLastRadius, int iFirstColor, int iLastColor, float fLifeTime) {
        m_fX = fX;
        m_fY = fY;
        m_fFirstRadius = fFirstRadius;
        m_fLastRadius = fLastRadius;
        m_iFirstColor = iFirstColor;
        m_iLastColor = iLastColor;
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
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(30.0f * m_fLifeTime/m_fFirstLifeTime);
        p.setColor((int)(m_iFirstColor * m_fLifeTime/m_fFirstLifeTime + m_iLastColor * (1.0f - m_fLifeTime/m_fFirstLifeTime)));
        p.setAlpha(m_iAlpha);
        canvas.drawCircle(m_fX, m_fY, (int)(m_fFirstRadius * m_fLifeTime/m_fFirstLifeTime + m_fLastRadius * (1.0f - m_fLifeTime/m_fFirstLifeTime)), p);
    };
}
