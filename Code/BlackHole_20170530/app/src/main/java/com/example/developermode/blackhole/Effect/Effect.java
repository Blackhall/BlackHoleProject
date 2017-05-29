package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;

/**
 * Created by DeveloperMode on 2017-05-26.
 */

public class Effect {
    protected float m_fX;
    protected float m_fY;
    protected float m_fLifeTime;

    public Effect() {
        m_fX = 0.0f;
        m_fY = 0.0f;
        m_fLifeTime = 0.0f;
    }
    public  void SetPosition(float fX, float fY) {
        m_fX = fX;
        m_fY = fY;
    }

    public void Init() {};
    public void Destroy() {};
    public void Update(float fElapsedTime) {};
    public void Render(Canvas canvas) {};
}
