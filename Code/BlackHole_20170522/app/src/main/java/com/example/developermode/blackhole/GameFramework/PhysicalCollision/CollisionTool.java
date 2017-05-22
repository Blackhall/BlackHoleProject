package com.example.developermode.blackhole.GameFramework.PhysicalCollision;

import android.renderscript.Float2;

import com.example.developermode.blackhole.Game.Planet;
import com.example.developermode.blackhole.GameFramework.GraphicObject;

import java.util.Vector;

/**
 * Created by DeveloperMode on 2017-04-25.
 */

public class CollisionTool {
    public static AABB MoveAABB(AABB _aabb, Float2 _shift)
    {
        return new AABB(
                new Float2(_aabb.m_f2Max.x + _shift.x, _aabb.m_f2Max.y + _shift.y),
                new Float2(_aabb.m_f2Min.x + _shift.x, _aabb.m_f2Min.y + _shift.y));
    }

    public static boolean IsCollided(AABB _aabb1, AABB _aabb2)
    {
        if (_aabb1.m_f2Min.x >= _aabb2.m_f2Max.x) return false;
        if (_aabb1.m_f2Min.y >= _aabb2.m_f2Max.y) return false;
        if (_aabb1.m_f2Max.x <= _aabb2.m_f2Min.x) return false;
        if (_aabb1.m_f2Max.y <= _aabb2.m_f2Min.y) return false;

        return true;
    }

    public static float GetDistance(Float2 _pos1, Float2 _pos2)
    {
        return (float)Math.sqrt(Math.pow(_pos1.x - _pos2.x, 2.0) + Math.pow(_pos1.y - _pos2.y, 2.0));
    }

    public static void Float2Normalize(Float2 _out, Float2 _in)
    {
        float fVectorLength = GetDistance(_in, new Float2(0.0f, 0.0f));

        _out.x = _in.x / fVectorLength;
        _out.y = _in.y / fVectorLength;
    }

    public static float Float2Dot(Float2 _v1, Float2 _v2)
    {
        return _v1.x * _v2.x + _v1.y * _v2.y;
    }

    public static void ImpurseBasedCollisionResolution(Planet _plnt1, Planet _plnt2)
    {
        // 충돌을 확인한다.
        float fDistance = GetDistance(new Float2(_plnt1.GetX(), _plnt1.GetY()), new Float2(_plnt2.GetX(), _plnt2.GetY()));

        float fRadiusA = _plnt1.GetRedius();
        float fRadiusB = _plnt2.GetRedius();

        if (fDistance > fRadiusA + fRadiusB) return;

        Float2 f2ContactNormal = new Float2();
        Float2 f2NormalVelocity = new Float2();

        // 접촉 노멀을 구한다.
        Float2Normalize(f2ContactNormal, new Float2(_plnt2.GetX() -_plnt1.GetX(), _plnt2.GetY() -_plnt1.GetY()));

        // 노멀 속도를 구한다.
        float fDot = Float2Dot(_plnt1.GetVelocity(), f2ContactNormal);

        f2NormalVelocity.x = fDot * f2ContactNormal.x;
        f2NormalVelocity.y = fDot * f2ContactNormal.y;

        Float2 f2J = new Float2();
        f2J.x = -(_plnt1.GetVelocity().x - _plnt2.GetVelocity().x) * (1.0f + 1.3f) * ((_plnt1.GetMass() * _plnt2.GetMass())/(_plnt1.GetMass() + _plnt2.GetMass()));
        f2J.y = -(_plnt1.GetVelocity().y - _plnt2.GetVelocity().y) * (1.0f + 1.3f) * ((_plnt1.GetMass() * _plnt2.GetMass())/(_plnt1.GetMass() + _plnt2.GetMass()));

        _plnt1.SetVelocity(new Float2(f2J.x * (1.0f / _plnt1.GetMass()) + _plnt1.GetVelocity().x,
                f2J.y * (1.0f / _plnt1.GetMass()) + _plnt1.GetVelocity().y));
        _plnt2.SetVelocity(new Float2(-f2J.x * (1.0f / _plnt2.GetMass()) + _plnt2.GetVelocity().x,
                -f2J.y * (1.0f / _plnt2.GetMass()) + _plnt2.GetVelocity().y));
    }
}
