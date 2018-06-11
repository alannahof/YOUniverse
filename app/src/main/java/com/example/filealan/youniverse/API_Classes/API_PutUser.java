package com.example.filealan.youniverse.API_Classes;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class API_PutUser extends AsyncTask<String, Void, User_Object>{

    Activity mainActivity;
    public IUserAsyncResponse delegate = null;
    User_Object user;

    public static final String REQUEST_METHOD = "PUT";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    public API_PutUser (IUserAsyncResponse responseActivity, Activity mainActivity, User_Object user) {
        this.delegate = responseActivity;
        this.mainActivity = mainActivity;
        this.user = user;
    }

    @Override
    protected User_Object doInBackground(String... params) {

        String apiGetUrlString = params[0];

        User_Object user_background = null;

        try {
            //Create URL object
            URL apiGetUrl = new URL(apiGetUrlString);

            //Create a connection to the API
            HttpURLConnection request = (HttpURLConnection) apiGetUrl.openConnection();

            //Set methods and timeouts
            request.setRequestMethod(REQUEST_METHOD);
            request.setConnectTimeout(READ_TIMEOUT);
            request.setConnectTimeout(CONNECTION_TIMEOUT);
            request.setRequestProperty("Content-Type", "application/json");
            request.setRequestProperty("Accept", "application/json");
            request.setDoOutput(true);
            request.setDoInput(true);

            //Remove patient id variable (MongoDB object_id - $oid causes issues when PUT/POST to DB)
            user.setId(null);

            //Convert JSON object to access data
            Gson gson = new Gson();
            String user_json = gson.toJson(user, User_Object.class);
            Log.d("API", "PUT MEHOTD json " + user_json);

            //Write Patient JSON object to Request
            OutputStream outputStream = request.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(user_json);
            writer.close();
            outputStream.close();

            //Convert JSON object to access data
            user_background = gson.fromJson(new InputStreamReader((InputStream)request.getContent()), User_Object.class);

            return user_background;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(User_Object user_background) {
        // TODO execute UI updates as required
        delegate.processFinish(user_background);
    }


}
