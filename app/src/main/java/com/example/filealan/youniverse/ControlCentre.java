package com.example.filealan.youniverse;

import android.app.Activity;
import android.util.Log;

import com.example.filealan.youniverse.Layout_Classes.Alien_Guide_Page;
import com.example.filealan.youniverse.Layout_Classes.Avatar_Selection_Page;
import com.example.filealan.youniverse.Layout_Classes.Main_SignIn_Page;
import com.example.filealan.youniverse.Layout_Classes.Map_Page;
import com.example.filealan.youniverse.Layout_Classes.Profile_Page;
import com.example.filealan.youniverse.Layout_Classes.Settings_Page;
import com.example.filealan.youniverse.Layout_Classes.Sign_Up_Page;

public class ControlCentre {

    private static ControlCentre instance = null;
    private static Activity act;

    //Control Centre constructor
    public ControlCentre (Activity act) { this.act = act;}

    /**
     * Set layout from memory, defaults to activity main if no other layout remembered
     */
    public void setLayout(){
        //If statement or switch between the different layouts
        if (MainActivity.layout_state == R.layout.activity_main){
            Log.d ("Test", "Control Centre - Constructor - Main Activity");
            setLayout_MainLoginPage ();
        } else if (MainActivity.layout_state == R.layout.profile_page){
            setLayout_ProfilePage ();
        } else if (MainActivity.layout_state == R.layout.avatar_selection){
            setLayout_AvatarPage();
        } else if (MainActivity.layout_state == R.layout.setting_screen) {
            setLayout_SettingsPage();
        } else if (MainActivity.layout_state == R.layout.sign_up) {
            setLayout_SignUpPage();
        } else if (MainActivity.layout_state == R.layout.map_progress_screen){
            setLayout_MapPage();
        } else if (MainActivity.layout_state == R.layout.alien_guide){
            setLayout_AlienGuide();
        }else {
            //setLayout_GamePage ();
        }
    }

    /**
     * Method defined to set the layout and manage the UI for the Main Signup Class
     * Constructor is called with 'getInstance' method
     * This is so that only once instance of the Main Signup Page Class ever exists at one time (memory management)
     */
    public static void setLayout_MainLoginPage(){
        Log.d ("Test", "Control Centre - Set Layout Main SignUp");
        Main_SignIn_Page main = Main_SignIn_Page.getInstance(act);
    }

    /**
     * Method defined to set the layout and manage the UI for the Profile Page Class
     * Constructor is called with 'getInstance' method
     * This is so that only once instance of the Profile Page Class ever exists at one time (memory management)
     */
    public static void setLayout_ProfilePage(){
        Profile_Page profile = Profile_Page.getInstance(act);
    }

    /**
     * Method defined to set the layout and manage the UI for the Avatar Class
     * Constructor is called with 'getInstance' method
     * This is so that only once instance of the Avatar Page Class ever exists at one time (memory management)
     */
    public static void setLayout_AvatarPage(){
        Avatar_Selection_Page avatar = Avatar_Selection_Page.getInstance(act);
    }

    /**
     * Method to set the layout and manage UI for Settings Page
     * Constructor called with 'getInstance' method so only one instance of the Settings Page class exists at one time
     */
    public static void setLayout_SettingsPage(){
        Settings_Page settings = Settings_Page.getInstance(act);
    }

    /**
     * Method to set the layout and manage UI for the sign up page
     * Constructor called with getInstance() to make sure only one instance of the sign up page class exists at a time
     */
    public static void setLayout_SignUpPage(){
        Sign_Up_Page signup = Sign_Up_Page.getInstance(act);
    }

    public static void setLayout_MapPage(){
        Map_Page mappage = Map_Page.getInstance(act);

    }


    //public static void setLayout_GamePage(){
        //Game_Page mappage = Game_Page.getInstance(act);

    //}

    public static void setLayout_AlienGuide(){
        Alien_Guide_Page alienguide = Alien_Guide_Page.getInstance(act);

    }

    /**
     * Returns the instance of the control centre
     * */
    public static ControlCentre getInstance(Activity act) {
        instance = new ControlCentre (act);
        return instance;
    }
}
