package com.example.developermode.blackhole.GameFramework;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by DeveloperMode on 2017-05-18.
 */

public interface IState {
    public void Init();
    public void Destroy();
    public void Update();
    public void Render(Canvas canvas);
    public boolean onKeyDown(int keyCode, KeyEvent event);
    public boolean onTouchEvent(MotionEvent event);
}
