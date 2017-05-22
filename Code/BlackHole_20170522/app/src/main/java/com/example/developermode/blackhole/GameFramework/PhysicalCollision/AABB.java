package com.example.developermode.blackhole.GameFramework.PhysicalCollision;

import android.renderscript.Float2;

/**
 * Created by DeveloperMode on 2017-05-20.
 */

public class AABB {
    Float2 m_f2Max;
    Float2 m_f2Min;

    public AABB(Float2 _max, Float2 _min)
    {
        m_f2Max = _max;
        m_f2Min = _min;
    }
}
