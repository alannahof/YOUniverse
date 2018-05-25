package com.example.filealan.youniverse.Layout_Classes;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Map_Page {

    private static Map_Page instance = null;
    Activity activity;

    public Map_Page(Activity act){
        act.setContentView(R.layout.map_progress_screen);
        activity = act;
        setMapLayout();
    }

    public void setMapLayout(){

        ImageButton settingsButton = (ImageButton)activity.findViewById(R.id.profile_settings);
        ImageView image1 = (ImageView)activity.findViewById(R.id.planet_placeholder1);
        ImageView image2 = (ImageView)activity.findViewById(R.id.planet_placeholder2);
//        Button popUp = (Button)activity.findViewById(R.id.popUpButton);
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;
//        final double popupHeight = height*0.6;
//        final double popupWidth = width*0.72;
//
//        popUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                try {
//
//                    LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    View layout = inflater.inflate(R.layout.alien_guide,null);
//                    PopupWindow pw = new PopupWindow(layout, (int)popupWidth, (int)popupHeight, true);
//                    pw.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//
//        });


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlCentre.setLayout_ProfilePage();
            }
        });
    }



    public static Map_Page getInstance(Activity act) {
        instance = new Map_Page(act);
        return instance;
    }
}