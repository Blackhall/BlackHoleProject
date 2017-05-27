package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.developermode.blackhole.Game.Planet;

/**
 * Created by DeveloperMode on 2017-05-26.
 */

public class TwinklingPlanetEffect extends Effect {
    private Planet m_plnt = null;
    private int m_iAlpha = 255;
    private float m_fFirstLifeTime;
    private int m_iFirstColor;
    private int m_iLastColor;
    private float m_fRadius;
    static final int iAlpha = 200;

    public TwinklingPlanetEffect(Planet plnt, float fRadius, int iFirstColor, int iLastColor, float fLifeTime) {
        m_plnt = plnt;
        m_fRadius = fRadius;
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
        p.setStyle(Paint.Style.FILL);
        p.setColor((int)(m_iFirstColor * m_fLifeTime/m_fFirstLifeTime + m_iLastColor * (1.0f - m_fLifeTime/m_fFirstLifeTime)));
        p.setAlpha(m_iAlpha);
        if(m_fRadius > 0.0f) {
            canvas.drawCircle(m_plnt.GetX(), m_plnt.GetY(), m_fRadius, p);
        }
        else {
            canvas.drawCircle(m_plnt.GetX(), m_plnt.GetY(), m_plnt.GetRedius(), p);
        }
    };
}
