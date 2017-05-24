package com.example.developermode.blackhole.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.developermode.blackhole.GameFramework.AppManager;
import com.example.developermode.blackhole.GameFramework.GraphicObject;
import com.example.developermode.blackhole.R;

/**
 * Created by DeveloperMode on 2017-05-21.
 */

public class BackGround extends GraphicObject {
    Bitmap m_layer2;

    public BackGround() {
        super(AppManager.getInstance().getBitmap(R.drawable.background));
    }
    void Update(float GameTime) {
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }
}
