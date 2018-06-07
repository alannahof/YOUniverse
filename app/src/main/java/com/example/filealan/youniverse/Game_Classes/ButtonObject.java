package com.example.filealan.youniverse.Game_Classes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ButtonObject {

        private Bitmap image;
        public int xX, yY;

        public ButtonObject(Bitmap bmp, int x, int y) {
            image = bmp;

            yY = y;
            xX = x;
        }


    public void draw(Canvas canvas) {
            canvas.drawBitmap(image, xX, yY, null);

        }
    }



