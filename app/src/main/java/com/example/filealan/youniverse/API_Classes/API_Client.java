package com.example.filealan.youniverse.API_Classes;

import android.app.Activity;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class API_Client implements IAPIClient {

        private static API_Client instance = null;
        private String apiKey = null;
        private static final String apiBaseUrl = "https://youniverse.herokuapp.com"; //Get Heroku Url's
        Activity mainActivity;
        User_Object user;

        protected API_Client (Activity activity, User_Object user) {
            this.mainActivity = activity;
            this.user = user;
        }

        public static API_Client getInstance(Activity act, User_Object user) {
            instance = new API_Client (act, user);
            return instance;
        }

    @Override
    public void apiGetUser(String patient_name) {
        String url = apiBaseUrl + "/user/" + patient_name; //Add the ending of the URL if changed
        Log.d("API", url);

        API_GetUser getdetails = new API_GetUser((IUserAsyncResponse)mainActivity, mainActivity, user);

        try {
            getdetails.execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apiPutUser(User_Object patient) {
//
//            String url = apiBaseUrl + "/"; //Add the ending of the URL if changed
//
//        //Instantiate new instance of ApiPutDetailRequest Class
//        API_PutUser putDetail = new API_PutUser ((IUserAsyncResponse) mainActivity, mainActivity, patient);
//
//        try {
//            putDetail.execute(url).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}

