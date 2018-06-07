package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

public class Footer_Page {

    private static Footer_Page instance = null;
    Activity activity;

    public Footer_Page(Activity act){
        activity = act;
        setFooterLayout ();
    }

    public void setFooterLayout(){

        LinearLayout footer = (LinearLayout) activity.findViewById (R.id.footer);
        ImageView settings = (ImageView) activity.findViewById (R.id.settings_footer);
        ImageView profile = (ImageView) activity.findViewById (R.id.profile_footer);
        ImageView map = (ImageView)activity.findViewById (R.id.back_map_footer);
        TextView tokens = (TextView)activity.findViewById (R.id.tokens_footer);

        //If they haven't yet selected an avatar then the footer will be invisible to avoid being able to navigate through
        if (MainActivity.selected_avatar == 0) {
            footer.setVisibility (View.INVISIBLE);
        } else {
            settings.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    ControlCentre.setLayout_SettingsPage ();
                }
            });

            profile.setImageResource (MainActivity.selected_avatar);
            profile.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    ControlCentre.setLayout_ProfilePage ();
                }
            });

            map.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    ControlCentre.setLayout_MapPage ();
                }
            });

        }


    }

    public static Footer_Page getInstance(Activity act) {
        instance = new Footer_Page (act);
        return instance;
    }

}
