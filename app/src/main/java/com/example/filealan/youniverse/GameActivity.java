package com.example.filealan.youniverse;

import android.os.Bundle;
import android.app.Activity;
import android.view.WindowManager;

import com.example.filealan.youniverse.Game_Classes.GameView;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameView (this, this));
    }

}
