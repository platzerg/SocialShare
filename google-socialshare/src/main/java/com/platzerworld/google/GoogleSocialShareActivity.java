package com.platzerworld.google;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.platzerworld.google.places.GooglePlaces;
import com.platzerworld.google.places.result.Result;

import java.io.IOException;


public class GoogleSocialShareActivity extends Activity {
    static final String TAG = GoogleSocialShareActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_social_share);

        Button btnGetPlaces = (Button) findViewById(R.id.btnGetPlaces);
        btnGetPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetNearbyPlaces placesTask = new GetNearbyPlaces();
                placesTask.execute("Cham");
            }
        });


    }

    class GetNearbyPlaces extends AsyncTask<String, Void, Result> {
        @Override
        protected Result doInBackground(String... params) {
            Result res = null;
            GooglePlaces googlePlaces = new GooglePlaces("AIzaSyD16oJOQ6USd_SKMCjHnLX6Oc8CkXiBpiQ");
            try {
                res = googlePlaces.getNearbyPlaces(49.240635, 12.673337);
                // 96b79713bd3e9d0b3dda88cae1595d3952cdbb5f
                //res = googlePlaces.getPlaceDetails("96b79713bd3e9d0b3dda88cae1595d3952cdbb5f");
                res.getResults();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(Result s)
        {
            Log.d(TAG, s.getResults().toString());
            finishActivity(s);
        }
    }

    private void finishActivity(Result result) {
        Log.d("GPL", "app");
        Intent data = new Intent();
        data.putExtra("google_places_result", result);
        setResult(RESULT_OK, data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.google_social_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
