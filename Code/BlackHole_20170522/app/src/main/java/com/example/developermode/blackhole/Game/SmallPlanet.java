package com.example.developermode.blackhole.Game;

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
        Float2 _max = new Float2(50.0f * 3.5f, 50.0f * 3.5f);
        Float2 _min = new Float2(-50.0f * 3.5f, -50.0f * 3.5f);
        m_AABB = new AABB(_max, _min);

        this.SetPosition(1440/2, 2560/2);
        this.InitSpriteData(350, 350, 1, 1);

        m_fRadius = 50.0f * 3.5f;
        m_fMass = 5.0f;
    }
    public void Update(long GameTime) {
        super.Update(GameTime);
    }
}
