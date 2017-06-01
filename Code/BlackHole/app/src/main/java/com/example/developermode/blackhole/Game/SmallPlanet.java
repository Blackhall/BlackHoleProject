package com.example.developermode.blackhole.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Float2;

import com.example.developermode.blackhole.GameFramework.AppManager;
import com.example.developermode.blackhole.GameFramework.PhysicalCollision.AABB;
import com.example.developermode.blackhole.R;

import java.util.Random;

/**
 * Created by DeveloperMode on 2017-05-21.
 */

public class SmallPlanet extends Planet {
    public SmallPlanet() {
        super(AppManager.getInstance().getBitmap(R.drawable.circle));
        Float2 _max = new Float2(50.0f * 3.62f, 50.0f * 4.0f);
        Float2 _min = new Float2(-50.0f * 3.62f, -50.0f * 4.0f);
        m_AABB = new AABB(_max, _min);

        this.SetPosition(1440/2, 2560/2);
        this.InitSpriteData((int)(100 * 4.0f), (int)(100 * 4.0f), 1, 1);

        m_fRadius = 50.0f * 4.0f;
        m_fMass = 30.0f;
        m_iHP = 500;
    }
    public void Update(float fElapsedTime) {
        super.Update(fElapsedTime);
        m_iAlpha = (int)(255.0f * (1.0f - (float)m_iHP/ 500.0f));
    }
}
