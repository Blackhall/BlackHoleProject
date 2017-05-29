package com.example.developermode.blackhole.Game;

import android.renderscript.Float2;

import com.example.developermode.blackhole.GameFramework.AppManager;
import com.example.developermode.blackhole.GameFramework.PhysicalCollision.AABB;
import com.example.developermode.blackhole.R;

/**
 * Created by DeveloperMode on 2017-05-22.
 */

public class BigPlanet extends Planet {
    public BigPlanet() {
        super(AppManager.getInstance().getBitmap(R.drawable.circle3));
        Float2 _max = new Float2(100.0f * 3.5f, 100.0f * 3.5f);
        Float2 _min = new Float2(-100.0f * 3.5f, -100.0f * 3.5f);
        m_AABB = new AABB(_max, _min);

        this.SetPosition(1440/2, 2560/2);
        this.InitSpriteData(700, 700, 1, 1);

        m_fRadius = 100.0f * 3.5f;
        m_fMass = 40.0f;
        m_iHP = 1500;
    }
    public void Update(float fElapsedTime) {
        super.Update(fElapsedTime);
        m_iAlpha = (int)(255.0f * (1.0f - (float)m_iHP/ 1500.0f));
    }
}
