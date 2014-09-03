package com.platzerworld.social;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import com.platzerworld.google.GoogleSocialShareActivity;
import com.platzerworld.google.places.GooglePlaces;
import com.platzerworld.google.places.models.GooglePlaceBase;
import com.platzerworld.google.places.models.Place;
import com.platzerworld.google.places.result.PlacesResult;
import com.platzerworld.google.places.result.Result;
import com.platzerworld.social.logger.Log;
import com.platzerworld.social.logger.LogFragment;
import com.platzerworld.social.logger.LogWrapper;
import com.platzerworld.social.logger.MessageOnlyLogFilter;


public class SocialShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_share);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
        initializeLogging();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.social_share, menu);
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

    public void initializeLogging() {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        // On screen logging via a fragment with a TextView.
        LogFragment logFragment = (LogFragment) getFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());

        Log.i("GPL", "Ready");
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_social_share, container, false);

            Button btnGetPlaces = (Button) rootView.findViewById(R.id.btnStartGooglePlaces);
            btnGetPlaces.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startGooglePlaces();
                }
            });
            return rootView;
        }
        private void startGooglePlaces(){
            Intent startGooglePlaceIntent = new Intent(getActivity().getApplicationContext(), GoogleSocialShareActivity.class);
            Bundle bundle = new Bundle();
            startActivityForResult(startGooglePlaceIntent, 0, bundle);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK && requestCode == 0) {
                if (data.hasExtra("google_places_result")) {
                    Result result = (Result) data.getExtras().getSerializable("google_places_result");
                    TextView edtResult = (TextView) getView().findViewById(R.id.edtResult);

                    for( GooglePlaceBase k: result.getResults() ) {
                        if(k instanceof Place){
                            Place place = (Place) k;
                            Log.i("GPL",
                                    "ID: " +place.getId()
                                    +"Name: " +place.getName()
                                    +" lat: " +place.getGeometry().location.lat
                                    +" lon: " +place.getGeometry().location.lng);
                        }
                    }
                    edtResult.setText( ((Place)result.getResults().get(0)).getPlaceId());
                }
            }
        }
    }
}
