package com.dsapps.json_demo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    public void findWeather(View view) {


        DownloadTask task = new DownloadTask();
        task.execute("http://api.openweathermap.org/data/2.5/weather?q="+editText.getText().toString()+"&APPID=714e3dfb46576b4386898deb1ad174ba");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = findViewById(R.id.city);
        textView = findViewById(R.id.info);



    }

    public class DownloadTask extends AsyncTask<String, Void, String>
    {


        @Override
        protected String doInBackground(String... urls) {

            String result="";
            URL url;
            HttpURLConnection urlConnection=null;

            try {
                url=new URL(urls[0]);
                urlConnection=(HttpURLConnection) url.openConnection();

                InputStream in=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);

                int data=reader.read();
                while(data!=-1)
                {
                    char current=(char)data;
                    result=result+current;
                    data=reader.read();
                }

                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG ).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG ).show();

            }

            return null;

        }

        @Override
        protected void onPostExecute(String result) {















































































































































































































































            try {


                JSONObject jsonObject=new JSONObject(result);

                String weatherInfo=jsonObject.getString("weather");
                Log.i("Weather content", weatherInfo);

                JSONArray ar=new JSONArray(weatherInfo);

                for(int i=0;i<ar.length();i++)
                {
                    JSONObject part=ar.getJSONObject(i);

                    Log.i("main", part.getString("main"));
                    Log.i("description", part.getString("description"));

                    textView.setText(part.getString("main")+"\n"+part.getString("description"));
                    textView.setVisibility(View.VISIBLE);

                }


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG ).show();

            }


        }
    }
}
