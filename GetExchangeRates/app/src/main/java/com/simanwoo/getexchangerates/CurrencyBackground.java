package com.simanwoo.getexchangerates;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.simanwoo.getexchangerates.MainActivity.USGS_REQUEST_URL;
import static com.simanwoo.getexchangerates.MainActivity.finalResult;

/**
 * Created by Siman on 5/2/2017.
 */

public class CurrencyBackground extends AsyncTask<URL, Void, ArrayList<Currency>> {
    private Context context;

    public CurrencyBackground(Context c){context = c;}

    @Override
    protected ArrayList<Currency> doInBackground(URL... urls) {
        String jsonResponse = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            url = new URL(USGS_REQUEST_URL);
            urlConnection = null;
            inputStream = null;
            if(url == null) return null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(30000 /* milliseconds */);
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200) { //if it is not 200, then there's an error
                inputStream = urlConnection.getInputStream();
                //now we deal with inputstream
                StringBuilder result = new StringBuilder();
                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    while (line != null) {
                        result.append(line);
                        line = reader.readLine();
                    }
                }
                jsonResponse = result.toString();
            }
        } catch (MalformedURLException e) {
            Log.e("URL creation failed", "Error with creating URL", e);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //now we have json respond
        if(TextUtils.isEmpty(jsonResponse)) return null;
        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);

            // If there are results in the features array
            if (true) {
                // Extract out the first feature (which is an earthquake)
                JSONObject properties = baseJsonResponse.getJSONObject("rates");
                JSONArray names = properties.names();
                ArrayList<Currency> result = new ArrayList<Currency>();

                for(int i = 0; i < names.length(); ++i){
                    String currentName = (String) names.get(i);
                    String curValue = properties.getString(currentName);
                    result.add(new Currency(currentName,curValue));
                }
                return result;
            }
        } catch (JSONException e) {
            Log.e("JSON Parsing", "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }

    //after doInBackground finishes, this function will automatically be called
    //result is whatever doInBackground returns
    @Override
    protected void onPostExecute(ArrayList<Currency> result) {
        if (result == null) {
            return;
        }
        finalResult = result;
        Intent intent = new Intent(context, LoadingFinished.class);
        context.startActivity(intent);
    }
}
