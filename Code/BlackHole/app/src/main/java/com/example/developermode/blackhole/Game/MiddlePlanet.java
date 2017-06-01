package com.example.developermode.blackhole.Game;

import android.renderscript.Float2;

import com.example.developermode.blackhole.GameFramework.AppManager;
import com.example.developermode.blackhole.GameFramework.PhysicalCollision.AABB;
import com.example.developermode.blackhole.R;

/**
 * Created by DeveloperMode on 2017-05-22.
 */

public class MiddlePlanet extends Planet {
    public MiddlePlanet() {
        super(AppManager.getInstance().getBitmap(R.drawable.circle2));
        Float2 _max = new Float2(75.0f * 4.0f, 75.0f * 4.0f);
        Float2 _min = new Float2(-75.0f * 4.0f, -75.0f * 4.0f);
        m_AABB = new AABB(_max, _min);

        this.SetPosition(1440/2, 2560/2);
        this.InitSpriteData((int)(150 * 4.0f), (int)(150 * 4.0f), 1, 1);

        m_fRadius = 75.0f * 4.0f;
        m_fMass = 35.0f;
        m_iHP = 1000;
    }
    public void Update(float fElapsedTime) {
        super.Update(fElapsedTime);
        m_iAlpha = (int)(255.0f * (1.0f - (float)m_iHP/ 1000.0f));
    }
}
