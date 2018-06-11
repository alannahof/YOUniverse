package com.example.filealan.youniverse;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.filealan.youniverse.API_Classes.API_Client;
import com.example.filealan.youniverse.API_Classes.IUserAsyncResponse;
import com.example.filealan.youniverse.API_Classes.User_Object;
import com.google.gson.Gson;

import static java.lang.Boolean.FALSE;

public class MainActivity extends Activity implements IUserAsyncResponse {

    /**In-application variables*/
    public static ControlCentre control;
    public static User_Object user;

    /**
     * Variables to be stored THROUGH-SESSIONS, i.e. persistent data that exists even when user closes application
     * (SharedPreferences)
     */
    //TODO PERSISTENT VARIABLES HERE **********************************************************************
    public static String username;
    public static int selected_avatar;
    public static boolean logged_in;
    public static int score;
    public static int tokens;

    //TODO SESSION VARIABLES HERE *************************************************************************
    public static int layout_state;

    //TODO FUTURE VARIABLES TO STORE HERE *****************************************************************
    public static int evaluation_level; //Which evaluations have they done
    public static int high_score; //In game high-score if play certain games multiple times
    public static String connection_method; //Which way do they log in (FB, email, etc)
    public static String planet_selected; //Remember which planet they are currently playing
    public static int background_selected; //To be able to toggle between backgrounds
    public static String level_achieved; //Remember which levels they have achieved

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main", "OnCreate");

//        //Mode 0 is MODE_PRIVATE. Means only this application can access and read/write by the calling application
//        SharedPreferences all_preferences = getSharedPreferences ("all_preferences", 0);
//
//        /**Retrieving the shared preferences and assigning them to the variables
//        Default to null if nothing has been saved
//        Ideally will be able to connect to the API and store this information in the database rather than in the application*/
//
//        logged_in = all_preferences.getBoolean ("logged_in", FALSE);
//
//        //Only retrieve the remaining shared preferences if they are logged in.
//        if (logged_in) {
//            Log.d("Login", "Main Activity Login TRUE");
//            username = all_preferences.getString ("username", null);
//            Log.d("Login", "" + username);
//            selected_avatar = all_preferences.getInt ("avatar", -1);
//            Log.d("Login", "" + selected_avatar);
//            score = all_preferences.getInt ("score", -1);
//            Log.d("Login", "" + score);
//            tokens = all_preferences.getInt ("tokens", -1);
//            Log.d("Login", "" + tokens);
//        } else {
//            Log.d("Login", "Main Activity Login FALSE");
//        }
//
//        //If savedInstanceState exists, will have saved the current layout
//        if (savedInstanceState !=null){
//            //Check if the layout state was saved
//            MainActivity.layout_state = savedInstanceState.getInt ("layout", R.layout.profile_page);
//        } else {
//            MainActivity.layout_state = R.layout.activity_main;
//        }

        Log.d("Avatars", R.drawable.avatar_1 + " av 1");
        Log.d("Avatars", R.drawable.avatar_2 + " av 2");
        Log.d("Avatars", R.drawable.avatar_3 + " av 3");
        Log.d("Avatars", R.drawable.avatar_4 + " av 4");
        Log.d("Avatars", R.drawable.avatar_5 + " av 5");
        Log.d("Avatars", R.drawable.avatar_6 + " av 6");

        //Initialises the control centre and sets the layout
        control = ControlCentre.getInstance (this);
        control.setLayout ();
    }

    @Override
    public void onStart(){
        super.onStart ();
        Log.d("Main", "OnStart");
    }

    @Override
    protected void onResume(){
        super.onResume ();
        Log.d("Main", "OnResume");


        System.out.println("the score is... " + score);


        //This is for when we resume the activity in-application
        //If you just finished the game then it will redirect you to the evaluation layout
        if (layout_state == 5) {
            ControlCentre.setLayout_EvaluationPage ();

        }
   }

   @Override
   protected void onStop(){
        super.onStop ();
        Log.d("Main", "OnStop");
   }

    /**
     * In the onPause() method, we will store the SharedPreferences that we want to persist between sessions
     */
    @Override
    protected void onPause() {
        super.onPause ();
        Log.d("Main", "OnPause");
//        SharedPreferences all_preferences = getSharedPreferences ("all_preferences", 0);
//        SharedPreferences.Editor allpref_editor = all_preferences.edit ();
//        allpref_editor.putString ("username", username);
//        allpref_editor.putInt ("avatar", selected_avatar);
//        allpref_editor.putBoolean ("logged_in", logged_in);
//        allpref_editor.putInt ("score", score);
//        allpref_editor.putInt ("tokens", tokens);
//        allpref_editor.commit ();
    }

    @Override
    protected void onSaveInstanceState (Bundle outState){

        Log.d("Main", "OnSaveInstance");

        //Save the session variable layout state in the save instance state
        outState.putInt("layout", MainActivity.layout_state);
        super.onSaveInstanceState (outState);
    }

    @Override
    public void processFinish(User_Object output) {

        user = output;

        // Serialize Patient and save to shared preferences
//        Gson gson = new Gson();
//        String user_json = gson.toJson(user, User_Object.class);
//        SharedPreferences all_preferences = getSharedPreferences("all_preferences", 0);
//        SharedPreferences.Editor allprefEditor = all_preferences.edit();
//
//        //Save the asynchronous objects in the corresponding SharedPreferences
//        allprefEditor.putString("user", user_json);
//        allprefEditor.commit();
    }

    public static void getPatientApi(Activity act, String user_name) {
        API_Client apiClient = API_Client.getInstance(act, user);
        apiClient.apiGetUser(user_name);

    }

    public static void putPatientApi(Activity act, User_Object user) {
        API_Client apiClient = API_Client.getInstance(act, user);
        apiClient.apiPutUser(user);
    }


}




