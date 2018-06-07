package com.example.filealan.youniverse;

import android.app.Activity;
import android.util.Log;

import com.example.filealan.youniverse.Layout_Classes.Alien_Guide_Page;
import com.example.filealan.youniverse.Layout_Classes.Avatar_Selection_Page;
import com.example.filealan.youniverse.Layout_Classes.Evaluation_Page;
import com.example.filealan.youniverse.Layout_Classes.Footer_Page;
import com.example.filealan.youniverse.Layout_Classes.Instructions;
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

        //When initialising the application, has looked at whether there was a Saved Instance State (session)
        //After the game and within the application (switching activities) this method is not called, so layout set in onResume (Main Activity)

        //Set up the footer page regardless of where you came from

        if(MainActivity.logged_in){

            //Then you want to go back to the last layout remembered or default to the map screen
            if (MainActivity.layout_state == R.layout.profile_page){
                setLayout_ProfilePage ();
            } else if (MainActivity.layout_state == R.layout.avatar_selection){
                setLayout_AvatarPage();
            } else if (MainActivity.layout_state == R.layout.setting_screen) {
                setLayout_SettingsPage();
            } else if (MainActivity.layout_state == R.layout.alien_guide){
                setLayout_AlienGuide();
            } else if (MainActivity.layout_state == R.layout.layout) {
                //This is the instruction layout
                setLayout_layout();
            }else if (MainActivity.layout_state == R.layout.evaluation){
                setLayout_EvaluationPage();
            } else {
                setLayout_ProfilePage ();
            }

        } else {
            setLayout_MainLoginPage ();
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

    public static void setLayout_FooterPage(){
        Footer_Page footerpage = Footer_Page.getInstance(act);
    }

    public static void setLayout_AlienGuide(){
        Alien_Guide_Page alienguide = Alien_Guide_Page.getInstance(act);

    }
    // sets the layout for the instructions page, sorry the xml and class are poorly named.
    public static void setLayout_layout(){
        Instructions inflateLayout = Instructions.getInstance(act);

    }
    //sets the layout for the evaluation post-game
    public static void setLayout_EvaluationPage(){
        Evaluation_Page evaluatePage = Evaluation_Page.getInstance(act);
    }

    /**
     * Returns the instance of the control centre
     * */
    public static ControlCentre getInstance(Activity act) {
        instance = new ControlCentre (act);
        return instance;
    }
}
