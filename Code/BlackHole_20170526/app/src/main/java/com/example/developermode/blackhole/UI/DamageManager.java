package com.example.developermode.blackhole.UI;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by DeveloperMode on 2017-05-25.
 */

public class DamageManager {
    private ArrayList<Damage> m_DamageList = new ArrayList<Damage>();

    public void RegisterDamageObj(Damage dmg) {
        m_DamageList.add(dmg);
    }

    public void Update(float fElapsedTime) {
        for(int i = m_DamageList.size() - 1; i >= 0; i--) {
            Damage dmg = m_DamageList.get(i);
            dmg.Update(fElapsedTime);
            dmg.m_fLifeTime -= fElapsedTime;
            if(dmg.m_fLifeTime <= 0) {
                m_DamageList.remove(i);
                return;
            }
        }
    }

    public void Render(Canvas canvas) {
        for(int i = m_DamageList.size() - 1; i >= 0; i--) {
            Damage dmg = m_DamageList.get(i);
            dmg.Render(canvas);
        }
    }
}
