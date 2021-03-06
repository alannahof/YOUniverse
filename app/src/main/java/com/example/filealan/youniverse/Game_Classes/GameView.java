package com.example.filealan.youniverse.Game_Classes;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {


        private MainThread thread;
        Activity activity2;

        private AlienObject characterAlien;
        CatchObjects privacy1, privacy2, security1, security2;
        public ObstacleObject pipe1, pipe2, pipe3;
        public ButtonObject leftButton, rightButton;
        public int score = 0;
        public static int gapHeight = 800;
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
        public boolean onTouchEvent(MotionEvent event) {

            int x = (int) event.getX();
            int y = (int) event.getY();
            System.out.println("X= " + x + " Y= " + y);

            if(MatchLeftPixels(y)){
                characterAlien.y = characterAlien.y - (characterAlien.yVelocity * 5);
                return true;
            }

            if(MatchRightPixels(y)){
                characterAlien.y = characterAlien.y + (characterAlien.yVelocity * 5);
                return true;
            }

            return super.onTouchEvent(event);
        }

        private boolean MatchLeftPixels(int y){
            if(y > 0 && y < screenHeight/2){
                return true;
            }
            return false;
        }

        private boolean MatchRightPixels(int y){
            if(y > screenHeight/2 && y < screenHeight){
                return true;
            }
            return false;
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
            privacy1.update();
            privacy2.update();
            security1.update();
            security2.update();

        }


        @Override
        public void draw(Canvas canvas)
        {

            super.draw(canvas);
            if(canvas!=null) {

//                Bitmap space = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.goalobjects), screenHeight, screenWidth);
//                Bitmap background = getRotatedBitmap(space, 90);
//                canvas.drawBitmap (background, 0,0,null);

                canvas.drawRGB (0,0,0);
                privacy1.draw(canvas);
                privacy2.draw(canvas);
                security1.draw(canvas);
                security2.draw(canvas);
                characterAlien.draw(canvas);
                pipe1.draw(canvas);
                pipe2.draw(canvas);
                pipe3.draw(canvas);
                drawScore(canvas);
                leftButton.draw(canvas);
            }
        }

        //work

        public void drawButtons(Canvas canvas){

        }

        public void drawScore(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(100);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.rotate(90);
            canvas.drawText("Score: " + score, 30, -screenWidth+100, paint);
            canvas.restore();
        }

        private void makeLevel() {

            Bitmap alienCharacter = getResizedBitmap(BitmapFactory.decodeResource(getResources(), MainActivity.selected_avatar), 300, 240);
            characterAlien = new AlienObject (getRotatedBitmap(alienCharacter, 90));

            Bitmap privacy_bmp = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.incognito), 100, 100);
            Bitmap privacy_bmp2 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.incognito), 100, 100);
            Bitmap security_bmp = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.padlock_blue), 100, 100);
            Bitmap security_bmp2 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.padlock_blue), 100, 100);

            privacy1 = new CatchObjects(getRotatedBitmap(privacy_bmp, 90), 2400, 500);
            privacy2 = new CatchObjects(getRotatedBitmap(privacy_bmp2, 90), 3800, 600);
            security1 = new CatchObjects(getRotatedBitmap(security_bmp, 90), 3000, 600);
            security2 = new CatchObjects(getRotatedBitmap(security_bmp2, 90), 4600, 500);

            Bitmap bmp;
            Bitmap bmp2;
            int y;
            int x;
            bmp = getResizedBitmap(BitmapFactory.decodeResource
                    (getResources(), R.drawable.troll1), 500, Resources.getSystem().getDisplayMetrics().heightPixels / 2);
            bmp2 = getResizedBitmap
                    (BitmapFactory.decodeResource(getResources(), R.drawable.troll3), 500, Resources.getSystem().getDisplayMetrics().heightPixels / 2);

            Bitmap arrowButton;
            arrowButton = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game_arrow), 300, 240);

            pipe1 = new ObstacleObject (bmp, bmp2, 2000, 100);
            pipe2 = new ObstacleObject (bmp, bmp2, 4500, 100);
            pipe3 = new ObstacleObject (bmp, bmp2, 3200, 100);

            leftButton = new ButtonObject(arrowButton, 50, 100);

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
            //bottom or top of txhe screen
            if (characterAlien.y + 240 < 0) {
                resetLevel(); }
            if (characterAlien.y > screenHeight) {
                resetLevel(); }

            score += 1;

            //If the pipe goes off the left of the screen,
            //put it forward at a randomized distance and height

            //added -1000 to make pipes not spawn too close together

            if (pipe1.xX + 500 < 0) {
                Random r = new Random();

                //figure out what nextInt is & bound
                int value1 = r.nextInt(500)-1000;
                int value2 = r.nextInt(500) ;

                pipe1.xX = screenWidth + value1 + 1000;
                pipe1.yY = value2 - 500;
            }

            if (pipe2.xX + 500 < 0) {
                Random r = new Random();
                int value1 = r.nextInt(500)-1000;

                int value2 = r.nextInt(500) ;

                pipe2.xX = screenWidth + value1 + 1000;
                pipe2.yY = value2 - 500;
            }

            if (pipe3.xX + 500 < 0) {
                Random r = new Random();
                int value1 = r.nextInt(500)-1000;

                int value2 = r.nextInt(500) ;

                pipe3.xX = screenWidth + value1 + 1000;
                pipe3.yY = value2 - 500;
            }

            if (privacy1.x + 500 < 0){
                Random r = new Random();
                int value1 = r.nextInt(750) - 750;

                int value2 = r.nextInt(750) ;

                privacy1.x = screenWidth + value1 + 1000;
                privacy1.y = value2 - 500;
            }

            if (privacy2.x + 500 < 0){
                Random r = new Random();
                int value1 = r.nextInt(750) - 750;

                int value2 = r.nextInt(750) ;

                privacy2.x = screenWidth + value1 + 1000;
                privacy2.y = value2 - 500;
            }

            if (security1.x + 500 < 0){
                Random r = new Random();
                int value1 = r.nextInt(750) - 750;

                int value2 = r.nextInt(750) ;

                security1.x = screenWidth + value1 + 1000;
                security1.y = value2 - 500;
            }

            if (security2.x + 500 < 0){
                Random r = new Random();
                int value1 = r.nextInt(750) - 750;

                int value2 = r.nextInt(750) ;

                security2.x = screenWidth + value1 + 1000;
                security2.y = value2 - 500;
            }

        }

        public void resetLevel() {
            if(score >= 100) {
                // Any in game variables need to be remembered here
                MainActivity.score = score;
//                Intent openMainActivity= new Intent(this.activity2, MainActivity.class);
//                openMainActivity.putExtra("SCORE", score);
//                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

//                I/System.out: Screen Width= 2392 Screen Height= 1440

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
