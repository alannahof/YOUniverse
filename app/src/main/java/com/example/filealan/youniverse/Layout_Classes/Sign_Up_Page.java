package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.filealan.youniverse.API_Classes.User_Object;
import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

public class Sign_Up_Page {

    private static Sign_Up_Page instance = null;
    Activity activity;

    /**Placeholder variables for when we are going to save this information
     * Will need to add a class to hash passwords*/
    String user_name;
    String email;
    String password;

public Sign_Up_Page(Activity act){
    act.setContentView(R.layout.sign_up);
    activity = act;
    MainActivity.layout_state = R.layout.sign_up;
    setSignUpLayout();
}

  /***
     * Set up the buttons and layout of the profile page
     */
public void setSignUpLayout(){

    final EditText signUp_userName = (EditText)activity.findViewById(R.id.signUp_userName);
    final EditText signUp_password  = (EditText)activity.findViewById(R.id.pword_signup);
    final EditText signUp_email  = (EditText)activity.findViewById(R.id.emailAddress_signup);
    Button signUp_Button = (Button)activity.findViewById(R.id.button_signup);



    signUp_Button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            user_name = signUp_userName.getText ().toString ();
            Log.d ("Username", "" + user_name);

            email = signUp_email.getText ().toString ();
            password = signUp_password.getText ().toString ();

            if (user_name.equals("") || user_name.equals (null)){
                //Tell the user that they didn't enter anything
            } else {

                MainActivity.user = new User_Object(user_name,password,2131230821,0,0,"test");

               MainActivity.putPatientApi(activity, MainActivity.user);

               ControlCentre.setLayout_MainLoginPage ();
            }
        }
    });

}
    public static Sign_Up_Page getInstance(Activity act) {
        instance = new Sign_Up_Page (act);
        return instance;
    }
}
