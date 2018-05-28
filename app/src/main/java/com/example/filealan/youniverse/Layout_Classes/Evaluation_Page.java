package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

public class Evaluation_Page {

    private static Evaluation_Page instance = null;
    Activity activity;

    public Evaluation_Page(Activity act){
        act.setContentView(R.layout.evaluation);
        activity = act;
        MainActivity.layout_state = R.layout.evaluation;
        setEvaluationLayout ();
    }

    public void setEvaluationLayout(){

        ImageView avatar = (ImageView) activity.findViewById (R.id.profile_avatar_placeholder);
        avatar.setImageResource (MainActivity.selected_avatar);

        ImageView goToProfile = (ImageView)activity.findViewById(R.id.profile_settings_button);
        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlCentre.setLayout_ProfilePage();
            }
        });

    }

    public static Evaluation_Page getInstance(Activity act) {
        instance = new Evaluation_Page (act);
        return instance;
    }


}
