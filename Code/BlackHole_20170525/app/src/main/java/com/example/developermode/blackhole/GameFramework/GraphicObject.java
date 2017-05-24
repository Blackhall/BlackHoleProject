package com.example.developermode.blackhole.GameFramework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by DeveloperMode on 2017-04-11.
 */

public class GraphicObject {
    protected Bitmap m_bitmap;
    protected float m_x;
    protected float m_y;

    public GraphicObject(Bitmap bitmap) {
        m_bitmap = bitmap;
        m_x = 0;
        m_y = 0;
    }

    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }

    public void SetPosition(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public float GetX() {
        return m_x;
    }

    public float GetY() { return m_y; }
}

