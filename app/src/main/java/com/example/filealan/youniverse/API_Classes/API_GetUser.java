package com.example.filealan.youniverse.API_Classes;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.filealan.youniverse.MainActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class API_GetUser extends AsyncTask<String, Void, User_Object> {

    Activity mainActivity;
    public IUserAsyncResponse responseActivity = null;
    User_Object user;

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    public API_GetUser(IUserAsyncResponse responseActivity, Activity mainActivity, User_Object user) {
        this.responseActivity = responseActivity;
        this.mainActivity = mainActivity;
        this.user = user;
    }

    @Override
    protected User_Object doInBackground(String... params) {

        Log.d("API", user + " returned");

        String apiGetUrlString = params[0];

        User_Object user = null;

        try {

            //Create URL object
            URL apiGetUrl = new URL (apiGetUrlString);
            Log.d("API", apiGetUrl + " url");

            //Create a connection to the API
            HttpURLConnection request = (HttpURLConnection) apiGetUrl.openConnection ();
            Log.d("API", request + " request");

            //Set methods and timeouts
            request.setRequestMethod (REQUEST_METHOD);
            request.setConnectTimeout (READ_TIMEOUT);
            request.setConnectTimeout (CONNECTION_TIMEOUT);

            //Convert JSON object to access data
            Gson gson = new Gson ();
            user = gson.fromJson (new InputStreamReader ((InputStream) request.getContent ()), User_Object.class);

            Log.d("API", user + " returned");

            MainActivity.user = user;

            return user;

        } catch (MalformedURLException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        return null;
    }

    @Override
    protected void onPostExecute(User_Object user) {
        // TODO execute UI updates as required
        responseActivity.processFinish (user);
    }

}

