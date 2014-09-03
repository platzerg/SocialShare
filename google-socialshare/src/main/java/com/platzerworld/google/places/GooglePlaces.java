package com.platzerworld.google.places;

import com.platzerworld.google.places.query.DetailsQuery;
import com.platzerworld.google.places.query.NearbySearchQuery;
import com.platzerworld.google.places.query.PhotoQuery;
import com.platzerworld.google.places.query.Query;
import com.platzerworld.google.places.query.TextSearchQuery;
import com.platzerworld.google.places.result.PhotoResult;
import com.platzerworld.google.places.result.PlaceDetailsResult;
import com.platzerworld.google.places.result.PlacesResult;
import com.platzerworld.google.places.result.Result;

import java.io.IOException;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class GooglePlaces {

    private String mApiKey = "";
    private AbstractSet<String> mSupportedPlaces;

    public GooglePlaces(String apiKey) {
        mApiKey = apiKey;
        loadSupportedPlaces();
    }

    /* ------------------------------------------------------ */
    /* NEARBYSEARCH */
    /* ------------------------------------------------------ */

    /**
     * @param types
     * @param keyword
     * @param radius
     * @param lat
     * @param lon
     * @return
     * @throws java.io.IOException
     */
    public Result getNearbyPlaces(List<String> types, String keyword, int radius, double lat, double lon) throws IOException {
        NearbySearchQuery query = new NearbySearchQuery(mApiKey, lat, lon);

        if (types != null) {
            for (String type : types) {
                if (isSupportedPlace(type))
                    query.addType(type);
            }
        }

        if (keyword != null && keyword != "") {
            query.setKeyword(keyword);
        }

        return getPlaces(query);
    }

    /**
     * @param types
     * @param radius
     * @param lat
     * @param lon
     * @return
     * @throws java.io.IOException
     */
    public Result getNearbyPlaces(List<String> types, int radius, double lat, double lon) throws IOException {
        return getNearbyPlaces(types, null, radius, lat, lon);
    }

    /**
     * @param type
     * @param keyword
     * @param radius
     * @param lat
     * @param lon
     * @return
     * @throws java.io.IOException
     */
    public Result getNearbyPlaces(String type, String keyword, int radius, double lat, double lon) throws IOException {
        return getNearbyPlaces(Arrays.asList(type), keyword, radius, lat, lon);
    }

    /**
     * 
     * DEFAULT NEARBY
     * 
     * @param sensor
     * @param radius
     * @param lat
     * @param lon
     * @return
     * @throws java.io.IOException
     */
    public Result getNearbyPlaces(double lat, double lon) throws IOException {
        return getPlaces(new NearbySearchQuery(mApiKey, lat, lon));
    }

    /**
     * 
     * DEFAULT NEARBY
     * 
     * @param sensor
     * @param radius
     * @param lat
     * @param lon
     * @return
     * @throws java.io.IOException
     */
    public Result getNearbyPlaces(int radius, double lat, double lon) throws IOException {
        return getPlaces(new NearbySearchQuery(mApiKey, lat, lon));
    }

    /**
     * 
     * DEFAULT NEARBY
     * 
     * @param sensor
     * @param radius
     * @param lat
     * @param lon
     * @return
     * @throws java.io.IOException
     */
    public Result getNearbyPlaces(int radius, double lat, double lon, boolean sensor) throws IOException {
        NearbySearchQuery query = new NearbySearchQuery(mApiKey, lat, lon);
        query.setSensor(sensor);
        return getPlaces(query);
    }

    /* ------------------------------------------------------ */
    /* TEXTSEARCH */
    /* ------------------------------------------------------ */

    /**
     * TextSearch with sensor choice
     * 
     * @param searchText
     * @param sensor
     * @return
     * @throws org.apache.http.client.ClientProtocolException
     * @throws org.json.JSONException
     * @throws java.io.IOException
     */
    public Result getTextPlaces(String searchText, boolean sensor) throws IOException {
        return getPlaces(new TextSearchQuery(mApiKey, searchText, sensor));
    }

    /**
     * TextSearch with sensor choice
     *
     * @param searchText
     * @param radius
     * @param lat
     * @param lon
     * @param sensor
     * @return
     * @throws org.apache.http.client.ClientProtocolException
     * @throws org.json.JSONException
     * @throws java.io.IOException
     */
    public Result getTextPlaces(String searchText, int radius, double lat, double lon, boolean sensor) throws IOException {
        return getPlaces(new TextSearchQuery(mApiKey, searchText, radius, lat, lon, sensor));
    }

    /**
     * TextSearch with sensor choice
     * 
     * @param searchText
     * @return
     * @throws org.apache.http.client.ClientProtocolException
     * @throws org.json.JSONException
     * @throws java.io.IOException
     */
    public Result getTextPlaces(String searchText) throws IOException {
        return getPlaces(new TextSearchQuery(mApiKey, searchText, true));
    }

    /* ------------------------------------------------------ */
    /* RADAR SEARCH */
    /* ------------------------------------------------------ */

    /* ------------------------------------------------------ */
    /* EVENT SEARCH */
    /* ------------------------------------------------------ */

    /* ------------------------------------------------------ */
    /* DETAILS SEARCH */
    /* ------------------------------------------------------ */

    /**
     * @param reference
     * @return
     * @throws org.json.JSONException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     */
    public Result getPlaceDetails(String reference) throws IOException {
        return getPlaces(new DetailsQuery(this.mApiKey, reference), PlaceDetailsResult.class);
    }

    /**
     * @param query
     * @return
     * @throws org.json.JSONException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     */
    public Result getPlaceDetails(Query query) throws IOException {
        return getPlaces(query, PlaceDetailsResult.class);
    }

    /* ------------------------------------------------------ */
    /* PHOTO SEARCH */
    /* ------------------------------------------------------ */

    /**
     * @param photoreference
     * @return
     * @throws java.io.IOException
     */
    public Result getPlacesPhoto(String photoreference) throws IOException {
        return sendRequest(new PhotoQuery(mApiKey, photoreference), PhotoResult.class);

    }

    /**
     * @param photoreference
     * @param maxWidth
     * @param maxHeight
     * @return
     * @throws java.io.IOException
     */
    public Result getPlacesPhoto(String photoreference, int maxWidth, int maxHeight) throws IOException {
        PhotoQuery p = new PhotoQuery(mApiKey, photoreference);
        p.setMaxHeight(maxHeight).setMaxWidth(maxWidth);
        return sendRequest(p, PhotoResult.class);
    }

    /* ------------------------------------------------------ */
    /* GENERIC SEARCH */
    /* ------------------------------------------------------ */

    /**
     * Base PlaceResult Request with Query object
     * 
     * @param query
     * @return
     * @throws org.json.JSONException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     */
    public Result getPlaces(Query query) throws IOException {
        return this.getPlaces(query, PlacesResult.class);
    }

    /**
     * Generic Request with custom Parsing return type
     * 
     * @param query
     * @param resultClass
     * @return
     * @throws org.json.JSONException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     */
    public Result getPlaces(Query query, Class<? extends Result> resultClass) throws IOException {
        return sendRequest(query, resultClass);
    }

    /**
     * @param query
     * @param resultClass
     * @return
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    private Result sendRequest(Query query, Class<? extends Result> resultClass) throws IOException {
        return query.getRequest().execute().parseAs(resultClass);
    }

    /**
     * Check if inserted string are in supported place types
     * 
     * @param placeType
     * @return
     */
    public boolean isSupportedPlace(String placeType) {
        return (mSupportedPlaces.contains(placeType));
    }

    /**
     * LIST OF SUPPORTED TYPE
     */
    private void loadSupportedPlaces() {
        mSupportedPlaces = new HashSet<String>();

        mSupportedPlaces.add("accounting");
        mSupportedPlaces.add("airport");
        mSupportedPlaces.add("amusement park");
        mSupportedPlaces.add("aquarium");
        mSupportedPlaces.add("art gallery");
        mSupportedPlaces.add("atm");
        mSupportedPlaces.add("bakery");
        mSupportedPlaces.add("bank");
        mSupportedPlaces.add("bar");
        mSupportedPlaces.add("beauty salon");
        mSupportedPlaces.add("bicycle store");
        mSupportedPlaces.add("book store");
        mSupportedPlaces.add("bowling alley");
        mSupportedPlaces.add("bus station");
        mSupportedPlaces.add("cafe");
        mSupportedPlaces.add("campground");
        mSupportedPlaces.add("car dealer");
        mSupportedPlaces.add("car rental");
        mSupportedPlaces.add("car repair");
        mSupportedPlaces.add("car wash");
        mSupportedPlaces.add("casino");
        mSupportedPlaces.add("cemetery");
        mSupportedPlaces.add("church");
        mSupportedPlaces.add("city hall");
        mSupportedPlaces.add("clothing store");
        mSupportedPlaces.add("convenience store");
        mSupportedPlaces.add("courthouse");
        mSupportedPlaces.add("dentist");
        mSupportedPlaces.add("department store");
        mSupportedPlaces.add("doctor");
        mSupportedPlaces.add("electrician");
        mSupportedPlaces.add("electronics store");
        mSupportedPlaces.add("embassy");
        mSupportedPlaces.add("establishment");
        mSupportedPlaces.add("finance");
        mSupportedPlaces.add("fire station");
        mSupportedPlaces.add("florist");
        mSupportedPlaces.add("food");
        mSupportedPlaces.add("funeral home");
        mSupportedPlaces.add("furniture store");
        mSupportedPlaces.add("gas station");
        mSupportedPlaces.add("general contractor");
        mSupportedPlaces.add("grocery or supermarket");
        mSupportedPlaces.add("gym");
        mSupportedPlaces.add("hair care");
        mSupportedPlaces.add("hardware store");
        mSupportedPlaces.add("health");
        mSupportedPlaces.add("hindu temple");
        mSupportedPlaces.add("home goods store");
        mSupportedPlaces.add("hospital");
        mSupportedPlaces.add("insurance agency");
        mSupportedPlaces.add("jewelry store");
        mSupportedPlaces.add("laundry");
        mSupportedPlaces.add("lawyer");
        mSupportedPlaces.add("library");
        mSupportedPlaces.add("liquor store");
        mSupportedPlaces.add("local government office");
        mSupportedPlaces.add("locksmith");
        mSupportedPlaces.add("lodging");
        mSupportedPlaces.add("meal delivery");
        mSupportedPlaces.add("meal takeaway");
        mSupportedPlaces.add("mosque");
        mSupportedPlaces.add("movie rental");
        mSupportedPlaces.add("movie theater");
        mSupportedPlaces.add("moving company");
        mSupportedPlaces.add("museum");
        mSupportedPlaces.add("night club");
        mSupportedPlaces.add("painter");
        mSupportedPlaces.add("park");
        mSupportedPlaces.add("parking");
        mSupportedPlaces.add("pet store");
        mSupportedPlaces.add("pharmacy");
        mSupportedPlaces.add("physiotherapist");
        mSupportedPlaces.add("place of worship");
        mSupportedPlaces.add("plumber");
        mSupportedPlaces.add("police");
        mSupportedPlaces.add("post office");
        mSupportedPlaces.add("real estate agency");
        mSupportedPlaces.add("restaurant");
        mSupportedPlaces.add("roofing contractor");
        mSupportedPlaces.add("rv park");
        mSupportedPlaces.add("school");
        mSupportedPlaces.add("shoe store");
        mSupportedPlaces.add("shopping mall");
        mSupportedPlaces.add("spa");
        mSupportedPlaces.add("stadium");
        mSupportedPlaces.add("storage");
        mSupportedPlaces.add("store");
        mSupportedPlaces.add("subway station");
        mSupportedPlaces.add("synagogue");
        mSupportedPlaces.add("taxi stand");
        mSupportedPlaces.add("train station");
        mSupportedPlaces.add("travel agency");
        mSupportedPlaces.add("university");
        mSupportedPlaces.add("veterinary care");
        mSupportedPlaces.add("zoo");
    }
}
