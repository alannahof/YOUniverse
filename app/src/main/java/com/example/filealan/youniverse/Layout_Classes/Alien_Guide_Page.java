package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.R;

import java.util.ResourceBundle;

public class Alien_Guide_Page {

    private static Alien_Guide_Page instance = null;
    Activity activity;

    public Alien_Guide_Page(Activity act){
        act.setContentView(R.layout.alien_guide);
        activity = act;
        setAlienPage();
    }

    public void setAlienPage(){

        ImageView speechBubble = (ImageView)activity.findViewById(R.id.speech);
        speechBubble.setImageResource(R.drawable.speech_bubble);
        TextView greetingsText = (TextView)activity.findViewById(R.id.greetingsText);
        ImageView alienGuide = (ImageView)activity.findViewById(R.id.alien_guide);
        alienGuide.setImageResource(R.drawable.alien_guide);
        ImageView planet1 = (ImageView)activity.findViewById(R.id.planet1);
        planet1.setImageResource(R.drawable.strineo);
        ImageView planet2 = (ImageView)activity.findViewById(R.id.planet2);
        planet2.setImageResource(R.drawable.ice_planet);

        Button yeah = (Button)activity.findViewById(R.id.yeahButton);

        yeah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlCentre.setLayout_MapPage();
            }
        });
    }


    public static Alien_Guide_Page getInstance(Activity act) {
        instance = new Alien_Guide_Page(act);
        return instance;
    }
}
