package com.jordanweaver.jordanweaver_solution;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 1/20/16.
 */
public class Network extends AsyncTask<String, Void, ArrayList<ApiObject> > {

    Context mContext;
    DisplayData theData;

    public Network(Context mContext, DisplayData mData) {
        this.mContext = mContext;
        this.theData = mData;
    }

    public interface DisplayData {
        public void updateArray(ArrayList<ApiObject> news);
    }

    @Override
    protected ArrayList<ApiObject> doInBackground(String... params) {

        String results = "";

        ArrayList<ApiObject> newsArray = new ArrayList<>();

        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService
                (Context.CONNECTIVITY_SERVICE);

        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info == null) {
                Log.e("No", "Network");
                Toast.makeText(mContext, "Device is not connected to a Network", Toast.LENGTH_LONG).show();
            } else {
                if (info.isConnected()){

                    try {
                        URL url = new URL(params[0]);

                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                        InputStream is = connection.getInputStream();
                        results = IOUtils.toString(is);

                        if (results.equals("Error")) {

                        } else {

                            String title;
                            String content;
                            String date;

                            try {
                                JSONObject mainObject = new JSONObject(results);
                                JSONArray articles = mainObject.getJSONArray("posts");


                                for(int i = 0; i<articles.length(); i++){
                                    JSONObject item = articles.getJSONObject(i);
                                    title = item.getString("title");
                                    content = item.getString("content");
                                    date = item.getString("date");

//                                    Log.d("Content", content + "");

                                    ApiObject newsObject = new ApiObject(title, content, date);

                                    if (newsArray.size() == 0) {
                                        newsArray.add(0, newsObject);
                                    } else {
                                        newsArray.add(newsArray.size(), newsObject);
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        return newsArray;
    }

    @Override
    protected void onPostExecute(ArrayList<ApiObject> apiObjects) {
        super.onPostExecute(apiObjects);

        if (apiObjects.size() != 0) {
            theData.updateArray(apiObjects);
        } else {
            Log.e("Network failed", "Please try again");

        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        cancel(true);
    }
}
