package com.example.developermode.blackhole.Game;

import android.renderscript.Float2;

import com.example.developermode.blackhole.GameFramework.AppManager;
import com.example.developermode.blackhole.GameFramework.PhysicalCollision.AABB;
import com.example.developermode.blackhole.GameFramework.PhysicalCollision.CollisionTool;
import com.example.developermode.blackhole.GameFramework.SpriteAnimation;
import com.example.developermode.blackhole.R;

import java.util.ArrayList;

/**
 * Created by DeveloperMode on 2017-05-22.
 */

public class Blackhole extends SpriteAnimation {
    protected float m_fRadius;
    static final float m_fGravitySize = 10000.0f;

    public Blackhole() {
        super(AppManager.getInstance().getBitmap(R.drawable.blackhole));
        this.SetPosition((int)(1440.0f / 2.0f), (int)(2560.0f / 2.0f));
        this.InitSpriteData((int)(300.0f * 4.0f), (int)(300.0f * 4.0f), 1, 1);

        m_fRadius = 150.0f * 3.62f;
    }

    public void PullPlanet(Planet plnt, float fElapsedTime) {
        Float2 f2ToBlackhole = new Float2(m_x - plnt.GetX(), m_y - plnt.GetY());
        CollisionTool.Float2Normalize(f2ToBlackhole, f2ToBlackhole);

        Float2 f2PostVelocity = plnt.GetVelocity();
        f2PostVelocity.x = f2PostVelocity.x + (m_fGravitySize / plnt.GetMass()) * f2ToBlackhole.x * fElapsedTime;
        f2PostVelocity.y = f2PostVelocity.y + (m_fGravitySize / plnt.GetMass()) * f2ToBlackhole.y * fElapsedTime;

        plnt.SetPosition(
                (int)(plnt.GetX() + (f2PostVelocity.x + plnt.GetVelocity().x) * fElapsedTime / 2.0f),
                (int)(plnt.GetY() + (f2PostVelocity.y + plnt.GetVelocity().y) * fElapsedTime / 2.0f));

        plnt.SetVelocity(f2PostVelocity);
    }

    public void AssignNumToPlanet(ArrayList<Planet> plntList, Planet plnt) {
        float fDistance = CollisionTool.GetDistance(new Float2(m_x, m_y), new Float2(plnt.GetX(), plnt.GetY()));
        if(fDistance > m_fRadius || plnt.m_iAssignedNum != 0) return;

        int iAssignedNum = 1;
        boolean bIsAssignable = true;

        while(true) {
            bIsAssignable = true;
            for(int i = 0; i < plntList.size(); i++) {
                if(plntList.get(i).equals(plnt)) continue;
                if(iAssignedNum == plntList.get(i).m_iAssignedNum) {
                    iAssignedNum++;
                    bIsAssignable = false;
                    break;
                }
            }
            if(bIsAssignable) {
                plnt.m_iAssignedNum = iAssignedNum;
                break;
            }
        }

        plnt.m_iAssignedNum = iAssignedNum;
    }
    public void Update(long GameTime) {
        super.Update(GameTime);
    }
}
