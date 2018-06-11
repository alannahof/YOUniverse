//package com.example.filealan.youniverse.API_Classes;
//
//import android.app.Activity;
//import android.os.AsyncTask;
//
//import com.google.gson.Gson;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class API_PutUser extends AsyncTask<String, Void, User_Object>{
//
//        Activity mainActivity;
//        public IUserAsyncResponse responseActivity = null;
//        User_Object user;
//
//        public static final String REQUEST_METHOD = "PUT";
//        public static final int READ_TIMEOUT = 15000;
//        public static final int CONNECTION_TIMEOUT = 15000;
//
//        public API_PutUser (IUserAsyncResponse responseActivity, Activity mainActivity, User_Object user) {
//            this.responseActivity = responseActivity;
//            this.mainActivity = mainActivity;
//            this.user = user;
//        }
//
//        @Override
//        protected User_Object doInBackground(String... params) {
//
//         ..
//        }
//
//        @Override
//        protected void onPostExecute(User_Object user) {
//            // TODO execute UI updates as required
//            responseActivity.processFinish(user);
//        }
//
//    }
