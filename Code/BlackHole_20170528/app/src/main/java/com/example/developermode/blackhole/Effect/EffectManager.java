package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by DeveloperMode on 2017-05-26.
 */

public class EffectManager {
    private ArrayList<Effect> m_EffectList = new ArrayList<Effect>();

    public void RegisterEffectObj(Effect effect) {
        m_EffectList.add(effect);
    }

    public void Update(float fElapsedTime) {
        for(int i = m_EffectList.size() - 1; i >= 0; i--) {
            Effect effect = m_EffectList.get(i);
            effect.Update(fElapsedTime);
            effect.m_fLifeTime -= fElapsedTime;
            if(effect.m_fLifeTime <= 0) {
                m_EffectList.remove(i);
                return;
            }
        }
    }

    public void Render(Canvas canvas) {
        for(int i = m_EffectList.size() - 1; i >= 0; i--) {
            Effect effect = m_EffectList.get(i);
            effect.Render(canvas);
        }
    }
}
