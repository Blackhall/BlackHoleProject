package com.example.developermode.blackhole.GameFramework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.developermode.blackhole.Game.GameState;

/**
 * Created by DeveloperMode on 2017-05-18.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private IState m_state;
    private GameViewThread m_thread;
    //private GraphicObject m_Image;
    //private SpriteAnimation m_spr;

    public GameView(Context context) {
        super(context);
        setFocusable(true);          // 키 입력 처리를 받기 위해서 // 터치만 쓴다면 필요없음.

        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());      //res 디렉토리에 있는 리소스를 가져옴.

        getHolder().addCallback(this);
        m_thread = new GameViewThread(getHolder(), this);   // surfaceCreated 함수 호출
        //m_Image = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.background_2));
        //m_spr = new SpriteAnimation(BitmapFactory.decodeResource(
        //        AppManager.getInstance().getResources(), R.drawable.player));
        //m_spr.InitSpriteData(104, 62, 60, 6);
        //m_spr.SetPosition(100, 120);

        ChangeGameState(new GameState());
    }

    public  void Update() {
        // 게임 상태를 업데이트
        long GameTime = System.currentTimeMillis();
        ////m_spr.Update(GameTime);
        m_state.Update();
    }
    //@Override
    protected void MyOnDraw(Canvas canvas) {
        //super.onDraw(canvas);

        //Bitmap _scratch = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        canvas.drawColor(Color.BLACK);
        //canvas.drawBitmap(_scratch, 10, 10, null);
        //m_Image.Draw(canvas);
        //m_spr.Draw(canvas);
        m_state.Render(canvas);
    }

    public void ChangeGameState(IState _state) {
        if(m_state != null) {
            m_state.Destroy();
        }
        _state.Init();
        m_state = _state;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        m_state.onKeyDown(keyCode, event);

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_state.onTouchEvent(event);
        //ChangeGameState(new CreditState());
        return true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        m_thread.setRunning(true);
        m_thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        m_thread.setRunning(false);
        while(retry) {
            try {
                m_thread.join();
                retry = false;
            } catch(InterruptedException e) {

            }
        }
    }
}
