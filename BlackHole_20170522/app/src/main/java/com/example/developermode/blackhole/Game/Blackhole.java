package com.example.developermode.blackhole.Game;

import android.renderscript.Float2;

import com.example.developermode.blackhole.GameFramework.AppManager;
import com.example.developermode.blackhole.GameFramework.PhysicalCollision.AABB;
import com.example.developermode.blackhole.GameFramework.PhysicalCollision.CollisionTool;
import com.example.developermode.blackhole.GameFramework.SpriteAnimation;
import com.example.developermode.blackhole.R;

/**
 * Created by DeveloperMode on 2017-05-22.
 */

public class Blackhole extends SpriteAnimation {
    protected float m_fRadius;
    static final float m_fGravitySize = 120.8f;

    public Blackhole() {
        super(AppManager.getInstance().getBitmap(R.drawable.blackhole));
        this.SetPosition((int)(1440.0f / 2.0f), (int)(2560.0f / 2.0f));
        this.InitSpriteData((int)(300.0f * 3.5f), (int)(300.0f * 3.5f), 1, 1);

        m_fRadius = 150.0f * 3.5f;
    }

    public void PullPlanet(Planet plnt, float fElapsedTime) {
        Float2 f2ToBlackhole = new Float2(m_x - plnt.GetX(), m_y - plnt.GetY());
        CollisionTool.Float2Normalize(f2ToBlackhole, f2ToBlackhole);

        Float2 f2PostVelocity = plnt.GetVelocity();
        f2PostVelocity.x = f2PostVelocity.x + m_fGravitySize * f2ToBlackhole.x * fElapsedTime;
        f2PostVelocity.y = f2PostVelocity.y + m_fGravitySize * f2ToBlackhole.y * fElapsedTime;

        plnt.SetPosition(
                (int)plnt.GetX() + (int)((f2PostVelocity.x + plnt.GetVelocity().x) * fElapsedTime / 2.0f),
                (int)plnt.GetY() + (int)((f2PostVelocity.y + plnt.GetVelocity().y) * fElapsedTime / 2.0f));

        plnt.SetVelocity(f2PostVelocity);
    }
    public void Update(long GameTime) {
        super.Update(GameTime);
    }
}
