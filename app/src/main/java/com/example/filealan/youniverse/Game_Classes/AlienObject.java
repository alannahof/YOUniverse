package com.example.filealan.youniverse.Game_Classes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class AlienObject {


    private Bitmap image;
    public int x, y;
    public int xVelocity = 10;
    public int yVelocity = 5;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public AlienObject (Bitmap bmp) {
        image = bmp;
        x = 500;
        y = 100;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);


    }
    public void update() {

        y += yVelocity;

    }

    }


