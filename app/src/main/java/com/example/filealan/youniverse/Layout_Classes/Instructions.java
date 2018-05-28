package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.filealan.youniverse.GameActivity;
import com.example.filealan.youniverse.R;

public class Instructions {

    private static Instructions instance = null;
    Activity activity;

    public Instructions (Activity act){
        act.setContentView(R.layout.layout);
        activity = act;
        setInstructionsLayout();
    }

    public void setInstructionsLayout(){

        Button enterGame = (Button)activity.findViewById(R.id.commenceGame);


        enterGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, GameActivity.class));
            }
        });
    }



    public static Instructions getInstance(Activity act) {
        instance = new Instructions(act);
        return instance;
    }
}
