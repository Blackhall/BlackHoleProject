package com.example.developermode.blackhole.Game;

import android.graphics.Bitmap;
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

    public Planet(Bitmap bitmap) {
        super(bitmap);
    }

    public float GetRedius() { return m_fRadius; }
    public float GetMass() { return  m_fMass; }
    public void SetVelocity(Float2 _v) { m_f2Velocity = _v; }
    public Float2 GetVelocity() { return m_f2Velocity; }

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
}
