package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

public class Main_SignIn_Page {

    private static Main_SignIn_Page instance = null;
    Activity activity;

    /**Placeholder variable for user_name*/
    String user_name;

    /**
     * Constructor to set up the Main Page
     * This corresponds with activity_main.xml
     * */
    public Main_SignIn_Page(Activity act){
        Log.d ("Test", "Main SignUp - Constructor");

        act.setContentView(R.layout.activity_main);
        activity = act;
        MainActivity.layout_state = R.layout.activity_main;
        setUpMainPage ();
    }

    /**
     * Sets up the buttons and functionality of the main SignIn page
     * */
    public void setUpMainPage() {

       TextView clickText = (TextView) activity.findViewById (R.id.clickText);
       final TextView userName = (TextView) activity.findViewById (R.id.userName);
       TextView password = (TextView) activity.findViewById (R.id.password);
       TextView welcomeText = (TextView) activity.findViewById (R.id.welcomeText);

       final EditText userNameField = (EditText) activity.findViewById (R.id.userNameField);
       EditText  passwordField = (EditText) activity.findViewById (R.id.passwordField);
       Button  proceedButton = (Button) activity.findViewById (R.id.proceedButton);

       clickText.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ControlCentre.setLayout_SignUpPage();
           }
       });

        Log.d ("Username before proceed", "" + user_name);
       proceedButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                user_name = userNameField.getText ().toString ();

                if(user_name.equals ("") || user_name.equals (null)){
                    //What to do if they don't enter a username
                } else {

                    //Set up what needs to be done. User name checked, password checked, etc etc
                    MainActivity.username = user_name;
                    ControlCentre.setLayout_AvatarPage ();
                }
            }
        });
    }

    /**
     * This method sets up the Main SignIn Page to have only one instance
     * One instance of the class will exist at one time
     * The class is instantiated using the getInstance method which always overwrites the previous instance
     */
    public static Main_SignIn_Page getInstance(Activity act) {
        instance = new Main_SignIn_Page (act);
        return instance;
    }
}
