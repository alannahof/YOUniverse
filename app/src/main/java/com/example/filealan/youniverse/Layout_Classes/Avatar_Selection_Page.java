package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

import java.util.ArrayList;

public class Avatar_Selection_Page {

    private static Avatar_Selection_Page instance = null;
    Activity activity;
    ArrayList<Integer> avatars;
    int current_avatar;
    int currentListIndex = 0;

    /**
     * Constructor to set up the Avatar Selection Page
     * */
    public Avatar_Selection_Page(Activity act){
        act.setContentView(R.layout.avatar_selection);
        activity = act;
        MainActivity.layout_state = R.layout.avatar_selection;
        avatars = avatarArray ();
        setLayout ();
    }

    /**Create the ArrayList of avatars that can be selected
     * Can add as many images as we want to this list*/
    public ArrayList<Integer> avatarArray(){
        avatars = new ArrayList<Integer> ();
        avatars.add (R.drawable.astronaut_avatar);
        avatars.add (R.drawable.green_alien_antenna);
        avatars.add (R.drawable.robot);
        avatars.add (R.drawable.alien_legs_big);
        avatars.add (R.drawable.green_alien_face);
        avatars.add (R.drawable.rocket);
        avatars.add (R.drawable.worldwide);
        return avatars;
    }

    /**This method gets the next avatar in the selection.
    If get to the end of the list start over*/
    public int getNextAvatar(){
        currentListIndex++;
        if (currentListIndex >= avatars.size ()){
            currentListIndex = 0;
        }
        return avatars.get (currentListIndex);
    }

    /**This method gets the next avatar in the selection.
     If get to the end of the list start over*/
    public int getPreviousAvatar(){
        currentListIndex--;
        if (currentListIndex < 0){
            currentListIndex = avatars.size()-1;
        }
        return avatars.get (currentListIndex);
    }

    /**
     * Sets up the layout for this page
     * */
    public void setLayout(){
        TextView avatar_username_display = (TextView)activity.findViewById (R.id.avatar_username);
        avatar_username_display.setText (MainActivity.username);

        final ImageView avatar_back_button = (ImageView) activity.findViewById (R.id.avatar_button_back);
        avatar_back_button.setImageResource (R.drawable.arrow_white);

        final ImageView avatar_image = (ImageView) activity.findViewById (R.id.avatar_image);
        ImageView avatar_next_button = (ImageView) activity.findViewById (R.id.avatar_button_next);
        avatar_next_button.setImageResource (R.drawable.arrow_white);
        avatar_next_button.setRotation (180);

        final Button avatar_ready = (Button) activity.findViewById (R.id.avatar_button_ready);

        avatar_image.setImageResource (avatars.get(currentListIndex));

        avatar_ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ready button", "button pressed to map world");
                //Need to remember the avatar that was selected
                MainActivity.selected_avatar = avatars.get(currentListIndex);
                ControlCentre.setLayout_AlienGuide();
            }
        });

        avatar_back_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //Change the image of the avatar to the previous image
                current_avatar = getPreviousAvatar ();
                //set avatar_image to this
                avatar_image.setImageResource (current_avatar);
            }
        });

        avatar_next_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //Change the image of the avatar to the next image
                current_avatar = getNextAvatar ();
                avatar_image.setImageResource (current_avatar);
            }
        });

    }

    /**
     * This method sets up the Avatar Selection Page Page to have only one instance
     * One instance of the class will exist at one time
     * The class is instantiated using the getInstance method which always overwrites the previous instance
     */
    public static Avatar_Selection_Page getInstance(Activity act) {
        instance = new Avatar_Selection_Page (act);
        return instance;
    }

}
