package com.example.filealan.youniverse;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filealan.youniverse.Layout_Classes.Main_SignIn_Page;

public class MainActivity extends Activity {

    public ControlCentre control;
    public static int layout_state;

    /**
     *
     * Will need to set up the shared preferences
     *
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //Initialises the control centre and sets the layout
//        control = ControlCentre.getInstance (this);
//        if (savedInstanceState !=null){
//            //remember layout state
//        } else {
//            layout_state = R.layout.activity_main;
//        }
//        control.setLayout ();
        setContentView (R.layout.avatar_selection);
    }

    @Override
    protected void onPause() {
        super.onPause ();
    }

    @Override
    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState (outState);
    }
}






