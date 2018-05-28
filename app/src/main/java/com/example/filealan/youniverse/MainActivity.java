package com.example.filealan.youniverse;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filealan.youniverse.Layout_Classes.Main_SignIn_Page;

public class MainActivity extends Activity {

    public static ControlCentre control;
    public static int layout_state;
    public static String username;
    public static int selected_avatar;

    public static int score;


    /**
     *
     * Will need to set up the shared preferences
     *
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Main", "OnCreate");

        //Initialises the control centre and sets the layout
        control = ControlCentre.getInstance (this);
        if (savedInstanceState !=null){
            //remember layout state
        } else {
            layout_state = R.layout.activity_main;
        }
        control.setLayout ();

    }

    @Override
    protected void onResume(){ super.onResume ();
        Log.d("Main", "OnResume");
        //Put code so that if you are coming from the game you are directed to another screen

        if (layout_state == 5) {
            setContentView (R.layout.profile_page);
        }
   }

    @Override
    protected void onPause() {
        super.onPause ();Log.d("Main", "OnPause");
    }

    @Override
    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState (outState);Log.d("Main", "OnSaveInstancec");
    }
}






