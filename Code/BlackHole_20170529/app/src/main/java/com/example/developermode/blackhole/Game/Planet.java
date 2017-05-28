package com.example.developermode.blackhole.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Float2;

import com.example.developermode.blackhole.GameFramework.PhysicalCollision.AABB;
import com.example.developermode.blackhole.GameFramework.SpriteAnimation;

/**
 * Created by DeveloperMode on 2017-05-20.
 */

public class Planet extends SpriteAnimation {
    protected float m_fRadius;
    protected float m_fMass;
    protected Float2 m_f2Velocity;
    protected AABB m_AABB;
    protected int m_iAssignedNum;
    protected int m_iHP;
    protected int m_iAlpha;

    public Planet(Bitmap bitmap) {
        super(bitmap);
        m_iAssignedNum = 0;
        m_iAlpha = 0;
    }

    public float GetRedius() { return m_fRadius; }
    public float GetMass() { return  m_fMass; }
    public void SetVelocity(Float2 _v) { m_f2Velocity = _v; }
    public Float2 GetVelocity() { return m_f2Velocity; }
    public int GetAssignedNum() { return m_iAssignedNum; }

    void Move(float GameTime)
    {
        m_x = m_x + m_f2Velocity.x * GameTime;
        m_y = m_y + m_f2Velocity.y * GameTime;
    }

    public void Update(float GameTime)
    {
        super.Update(GameTime);
        Move(GameTime);
    }

    @Override
    public void Draw(Canvas canvas) {
        //p.setAlpha(50);
        super.Draw(canvas);
        //p.setAlpha(50);
        Paint p = new Paint();
        p.setTextSize(100);
        p.setColor(Color.BLACK);
        if(m_iAssignedNum != 0) canvas.drawText(String.valueOf(m_iAssignedNum), m_x, m_y, p);
        p.setColor(Color.WHITE);
        p.setAlpha(m_iAlpha);
        canvas.drawCircle(m_x, m_y, m_fRadius, p);
    }
}
