package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

import java.util.ResourceBundle;

public class Profile_Page {

    private static Profile_Page instance = null;
    Activity activity;

    /**
     * Constructor to set up the User Profile Page
     * */
    public Profile_Page(Activity act){
        act.setContentView(R.layout.profile_page);
        activity = act;
        MainActivity.layout_state = R.layout.profile_page;
        setLayout ();
    }

    /***
     * Set up the buttons and layout of the profile page
     */
    public void setLayout(){

        TextView username = (TextView) activity.findViewById (R.id.profile_UserName);
        username.setText (MainActivity.username);

        ImageView avatar = (ImageView) activity.findViewById (R.id.profile_avatar_placeholder);
        avatar.setImageResource (MainActivity.selected_avatar);

        TextView profile_level_text = (TextView)activity.findViewById (R.id.profile_level_reached);
        TextView profile_level1 = (TextView) activity.findViewById (R.id.profile_level1);
        TextView profile_level2 = (TextView) activity.findViewById (R.id.profile_level2);
        TextView profile_level3 = (TextView) activity.findViewById (R.id.profile_level3);

        ImageView profile_settings = (ImageView)activity.findViewById (R.id.profile_settings_button);
        ImageView profile_back_map = (ImageView) activity.findViewById (R.id.profile_back_map_button);
        TextView profile_points = (TextView) activity.findViewById (R.id.profile_points);

        profile_settings.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //Go to the settings page once clicked
                ControlCentre.setLayout_SettingsPage ();
            }
        });

        avatar.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //Go to avatar customisation screen once clicked
                ControlCentre.setLayout_AvatarPage ();
            }
        });

        profile_back_map.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ControlCentre.setLayout_SettingsPage ();
            }
        });
    }


    /**
     * This method sets up the Profile Page to have only one instance
     * One instance of the class will exist at one time
     * The class is instantiated using the getInstance method which always overwrites the previous instance
     */
    public static Profile_Page getInstance(Activity act) {
        instance = new Profile_Page (act);
        return instance;
    }
}
