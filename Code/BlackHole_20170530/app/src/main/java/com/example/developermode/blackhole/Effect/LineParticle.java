package com.example.developermode.blackhole.Effect;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Float2;

/**
 * Created by DeveloperMode on 2017-05-29.
 */

public class LineParticle {
    public float m_fInitialLineLength;
    public float m_fLineLength;
    public float m_fX;
    public float m_fY;
    public float m_fInitialLineX1;
    public float m_fInitialLineY1;
    public float m_fInitialLineX2;
    public float m_fInitialLineY2;
    public float m_fLineX1;
    public float m_fLineY1;
    public float m_fLineX2;
    public float m_fLineY2;
    public float m_fVX;
    public float m_fVY;
    public double m_dCurRotDegree;
    public float m_fVRotation;      // degree / min
    public float m_fInitialLifeTime;
    public float m_fLineSize;
    public int m_iColor;
    public float m_fLifeTime;

    public LineParticle(float fLineLength, float fX, float fY, float fVX, float fVY, double dCurRotDegree,
                    float fVRotation, float fLineSize, int iColor, float fLifeTime) {
        m_fInitialLineLength = fLineLength;
        m_fLineLength = fLineLength;
        // 일종의 로컬 좌표계에 정의된 Line 모델
        m_fInitialLineX1 = -fLineLength / 2.0f;
        m_fInitialLineY1 = 0.0f;
        m_fInitialLineX2 = fLineLength / 2.0f;
        m_fInitialLineY2 = 0.0f;

        m_fX = fX;
        m_fY = fY;
        m_fVX = fVX;
        m_fVY = fVY;
        m_dCurRotDegree = dCurRotDegree;

        Float2 f2Line1 = new Float2(m_fInitialLineX1, m_fInitialLineY1);
        Float2 f2Line2 = new Float2(m_fInitialLineX2, m_fInitialLineY2);

        f2Line1.x = m_fInitialLineX1 * (float)Math.cos(Math.toRadians(dCurRotDegree)) - m_fInitialLineY1 * (float)Math.sin(Math.toRadians(dCurRotDegree));
        f2Line1.y = m_fInitialLineX1 * (float)Math.sin(Math.toRadians(dCurRotDegree)) + m_fInitialLineY1 * (float)Math.cos(Math.toRadians(dCurRotDegree));
        m_fLineX1 = f2Line1.x + m_fX;
        m_fLineY1 = f2Line1.y + m_fY;

        f2Line2.x = m_fInitialLineX2 * (float)Math.cos(Math.toRadians(dCurRotDegree)) - m_fInitialLineY2 * (float)Math.sin(Math.toRadians(dCurRotDegree));
        f2Line2.y = m_fInitialLineX2 * (float)Math.sin(Math.toRadians(dCurRotDegree)) + m_fInitialLineY2 * (float)Math.cos(Math.toRadians(dCurRotDegree));
        m_fLineX2 = f2Line2.x + m_fX;
        m_fLineY2 = f2Line2.y + m_fY;

        m_fVRotation = fVRotation;
        m_fLineSize = fLineSize;
        m_iColor = iColor;
        m_fInitialLifeTime = fLifeTime;
        m_fLifeTime = fLifeTime;
    }

    public void Update(float fElapsedTime) {
        // 시간 경과에 따른 모델 재정의
        m_fLineLength = m_fInitialLineLength * m_fLifeTime / m_fInitialLifeTime;
        m_fInitialLineX1 = -m_fLineLength / 2.0f;
        m_fInitialLineY1 = 0.0f;
        m_fInitialLineX2 = m_fLineLength / 2.0f;
        m_fInitialLineY2 = 0.0f;

        // 시간 경과에 따른 속도에 의한 위치 갱신
        m_fX = m_fX + (m_fVX + m_fVX) / 2.0f * fElapsedTime ;
        m_fY = m_fY + (m_fVY + m_fVY) / 2.0f * fElapsedTime ;

        // 시간 경과에 따른 회전 갱신
        m_dCurRotDegree += m_fVRotation * fElapsedTime;
        if (m_dCurRotDegree >= 360.0) m_dCurRotDegree -= 360.0;

        Float2 f2Line1 = new Float2(m_fLineX1, m_fLineY1);
        Float2 f2Line2 = new Float2(m_fLineX2, m_fLineY2);

        f2Line1.x = m_fInitialLineX1 * (float) Math.cos(Math.toRadians(m_dCurRotDegree)) - m_fInitialLineY1 * (float) Math.sin(Math.toRadians(m_dCurRotDegree));
        f2Line1.y = m_fInitialLineX1 * (float) Math.sin(Math.toRadians(m_dCurRotDegree)) + m_fInitialLineY1 * (float) Math.cos(Math.toRadians(m_dCurRotDegree));

        f2Line2.x = m_fInitialLineX2 * (float) Math.cos(Math.toRadians(m_dCurRotDegree)) - m_fInitialLineY2 * (float) Math.sin(Math.toRadians(m_dCurRotDegree));
        f2Line2.y = m_fInitialLineX2 * (float) Math.sin(Math.toRadians(m_dCurRotDegree)) + m_fInitialLineY2 * (float) Math.cos(Math.toRadians(m_dCurRotDegree));

        // 최종 Line 갱신
        m_fLineX1 = f2Line1.x + m_fX;
        m_fLineY1 = f2Line1.y + m_fY;
        m_fLineX2 = f2Line2.x + m_fX;
        m_fLineY2 = f2Line2.y + m_fY;
    }

    public void Render(Canvas canvas) {
        Paint p = new Paint();
        p.setStrokeWidth(m_fLineSize);
        p.setColor(m_iColor);
        canvas.drawLine(m_fLineX1, m_fLineY1, m_fLineX2, m_fLineY2, p);
    }
}
