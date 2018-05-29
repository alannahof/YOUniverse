package com.example.filealan.youniverse.Game_Classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.GameActivity;
import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {


        private MainThread thread;
        Activity activity2;

        private AlienObject characterAlien;
        public ObstacleObject pipe1, pipe2, pipe3;
        public int score = 0;
        public static int gapHeight = 600;
        public static int velocity = 10;
        private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        public GameView(Context context, Activity act2) {
            super(context);
            activity2 = act2;
            MainActivity.layout_state = 5; //Randomly picked Alannah's favourite number, 5 has no meaning
            getHolder().addCallback(this);
            thread = new MainThread(getHolder(), this);
            setFocusable(true);

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            characterAlien.y = characterAlien.y - (characterAlien.yVelocity * 10);
            return super.onTouchEvent(event);
        }


        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            makeLevel();

            thread.setRunning(true);
            thread.start();

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            while (retry) {
                try {
                    thread.setRunning(false);
                    thread.join();

                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                retry = false;
            }
        }

        public void update() {
            logic();
            characterAlien.update();
            pipe1.update();
            pipe2.update();
            pipe3.update();
        }


        @Override
        public void draw(Canvas canvas)
        {

            super.draw(canvas);
            if(canvas!=null) {

                canvas.drawRGB (72,61,139);
                characterAlien.draw(canvas);
                pipe1.draw(canvas);
                pipe2.draw(canvas);
                pipe3.draw(canvas);
                drawScore(canvas);
            }
        }

        public void drawScore(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(50);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.rotate(90);
            canvas.drawText("Score: " + score, 30, -50, paint);
            canvas.restore();
        }

        private void makeLevel() {

            Bitmap alienCharacter = getResizedBitmap(BitmapFactory.decodeResource(getResources(), MainActivity.selected_avatar), 300, 240);
            characterAlien = new AlienObject (getRotatedBitmap(alienCharacter, 90));

            Bitmap bmp;
            Bitmap bmp2;
            int y;
            int x;
            bmp = getResizedBitmap(BitmapFactory.decodeResource
                    (getResources(), R.drawable.robot), 500, Resources.getSystem().getDisplayMetrics().heightPixels / 2);
            bmp2 = getResizedBitmap
                    (BitmapFactory.decodeResource(getResources(), R.drawable.robot), 500, Resources.getSystem().getDisplayMetrics().heightPixels / 2);


            pipe1 = new ObstacleObject (bmp, bmp2, 2000, 100);
            pipe2 = new ObstacleObject (bmp, bmp2, 4500, 100);
            pipe3 = new ObstacleObject (bmp, bmp2, 3200, 100);

        }

        public Bitmap getRotatedBitmap(Bitmap bm, float degrees){

            int width = bm.getWidth();
            int height = bm.getHeight();

            Matrix matrix = new Matrix();
            matrix.postRotate(degrees);

            Bitmap rotatedBitmap =
                    Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
            bm.recycle();

            return rotatedBitmap;
        }

        public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
            int width = bm.getWidth();
            int height = bm.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // CREATE A MATRIX FOR THE MANIPULATION
            Matrix matrix = new Matrix();
            // RESIZE THE BIT MAP
            matrix.postScale(scaleWidth, scaleHeight);

            // "RECREATE" THE NEW BITMAP
            Bitmap resizedBitmap =
                    Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
            bm.recycle();
            return resizedBitmap;
        }


        public void logic() {

            //Detect if the character is touching one of the pipes
            if (characterAlien.y < pipe1.yY + (screenHeight / 2) - (gapHeight / 2)
                    && characterAlien.x + 300 > pipe1.xX && characterAlien.x < pipe1.xX + 500)
            { resetLevel(); }

            if (characterAlien.y < pipe2.yY + (screenHeight / 2) - (gapHeight / 2)
                    && characterAlien.x + 300 > pipe2.xX && characterAlien.x < pipe2.xX + 500)
            { resetLevel(); }

            if (characterAlien.y < pipe3.yY + (screenHeight / 2) - (gapHeight / 2)
                    && characterAlien.x + 300 > pipe3.xX && characterAlien.x < pipe3.xX + 500)
            { resetLevel(); }

            if (characterAlien.y + 240 > (screenHeight / 2) + (gapHeight / 2) + pipe1.yY
                    && characterAlien.x + 300 > pipe1.xX && characterAlien.x < pipe1.xX + 500)
            { resetLevel(); }

            if (characterAlien.y + 240 > (screenHeight / 2) + (gapHeight / 2) + pipe2.yY
                    && characterAlien.x + 300 > pipe2.xX && characterAlien.x < pipe2.xX + 500)
            { resetLevel(); }

            if (characterAlien.y + 240 > (screenHeight / 2) + (gapHeight / 2) + pipe3.yY
                    && characterAlien.x + 300 > pipe3.xX && characterAlien.x < pipe3.xX + 500)
            { resetLevel(); }

            //Detect if the character has gone off the
            //bottom or top of the screen
            if (characterAlien.y + 240 < 0) {
                resetLevel(); }
            if (characterAlien.y > screenHeight) {
                resetLevel(); }

            score += 1;

            //If the pipe goes off the left of the screen,
            //put it forward at a randomized distance and height
            if (pipe1.xX + 500 < 0) {
                Random r = new Random();
                int value1 = r.nextInt(500);
                int value2 = r.nextInt(500);
                pipe1.xX = screenWidth + value1 + 1000;
                pipe1.yY = value2 - 250;
            }

            if (pipe2.xX + 500 < 0) {
                Random r = new Random();
                int value1 = r.nextInt(500);
                int value2 = r.nextInt(500);
                pipe2.xX = screenWidth + value1 + 1000;
                pipe2.yY = value2 - 250;
            }

            if (pipe3.xX + 500 < 0) {
                Random r = new Random();
                int value1 = r.nextInt(500);
                int value2 = r.nextInt(500);
                pipe3.xX = screenWidth + value1 + 1000;
                pipe3.yY = value2 - 250;
            }
        }

        public void resetLevel() {

            if(score >= 100) {
                // Any in game variables need to be remembered here
                MainActivity.score = score;
//                Intent openMainActivity= new Intent(this.activity2, MainActivity.class);
////                openMainActivity.putExtra("SCORE", score);
//                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                activity2.finish();
            }

            //resets level
            characterAlien.y = 100;
            pipe1.xX = 2000;
            pipe1.yY = 0;
            pipe2.xX = 4500;
            pipe2.yY = 200;
            pipe3.xX = 3200;
            pipe3.yY = 250;
            score = 0;
        }

    }
