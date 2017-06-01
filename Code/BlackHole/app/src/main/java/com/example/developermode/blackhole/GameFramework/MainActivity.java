package com.example.developermode.blackhole.GameFramework;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.developermode.blackhole.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        AppManager.getInstance().setVibrator((Vibrator) getSystemService(Context.VIBRATOR_SERVICE));

        setContentView(new GameView(this));
    }
}
