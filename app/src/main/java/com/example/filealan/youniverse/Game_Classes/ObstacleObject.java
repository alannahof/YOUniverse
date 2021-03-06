package com.example.filealan.youniverse.Game_Classes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ObstacleObject {

        private Bitmap image;
        private Bitmap image2;
        public int xX, yY;
        public int xVelocity = 10;
        private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        public ObstacleObject (Bitmap bmp, Bitmap bmp2, int x, int y) {
            image = bmp;
            image2 = bmp2;
            yY = y;
            xX = x;
        }


        public void draw(Canvas canvas) {
            canvas.drawBitmap(image, xX, -(GameView.gapHeight / 2) + yY, null);
            canvas.drawBitmap(image2,xX, ((screenHeight / 2)
                    + (GameView.gapHeight / 2)) + yY, null);


        }
        public void update() {

            xX -= GameView.velocity;
        }

    public int getxX() {
        return xX;
    }

    public void setxX(int xX) {
        this.xX = xX;
    }

    public int getyY() {
        return yY;
    }

    public void setyY(int yY) {
        this.yY = yY;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }
}



