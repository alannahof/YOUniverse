package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.GameActivity;
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



        TextView points = (TextView) activity.findViewById(R.id.scoreText);
        points.setText("You got " + MainActivity.score + " points!");


        Log.d ("Button", "Inside Set Layout");
        ImageView avatar = (ImageView) activity.findViewById (R.id.profile_avatar_placeholder);

        ControlCentre.setLayout_FooterPage ();

        avatar.setImageResource (MainActivity.selected_avatar);

    }

    public static Evaluation_Page getInstance(Activity act) {
        instance = new Evaluation_Page (act);
        return instance;
    }


}
