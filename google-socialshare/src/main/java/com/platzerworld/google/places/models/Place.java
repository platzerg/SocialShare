package com.platzerworld.google.places.models;

import com.google.api.client.util.Key;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe Mastroeni - aka: Kataklisma E-Mail: m.giuseppe@a2plab.com
 * 
 */
public class Place implements GooglePlaceBase {

    private static final long serialVersionUID = 1983125240283709545L;

    @Key
    protected String id;

    @Key
    protected String place_id;

    @Key
    protected String name;

    @Key
    protected String icon;

    @Key
    protected String reference;

    @Key
    protected String scope;

    /**
     * Use this for Nearby Search instead formatted_address
     */
    @Key
    protected String vicinity;

    @Key
    protected Geometry geometry;


    @Key
    protected String formatted_address;

    public static class Geometry implements Serializable {

        private static final long serialVersionUID = 1670272243139023026L;
        @Key
        public Location location;

        @Key
        public Viewport viewport;
    }

    public static class Location implements Serializable {

        private static final long serialVersionUID = -1932110927792589446L;

        @Key
        public double lat;

        @Key
        public double lng;
    }

    public static class Viewport implements Serializable {

        private static final long serialVersionUID = -1932110927792589446L;

        @Key
        public Northeast northeast;

        @Key
        public Southwest southwest;
    }

    public static class Northeast implements Serializable {

        private static final long serialVersionUID = -1932110927792589446L;

        @Key
        public double lat;

        @Key
        public double lng;
    }

    public static class Southwest implements Serializable {

        private static final long serialVersionUID = -1932110927792589446L;

        @Key
        public double lat;

        @Key
        public double lng;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the place_id
     */
    public String getPlaceId() {
        return place_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @return the vicinity
     */
    public String getVicinity() {
        return vicinity;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @return the geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * @return the formatted_address
     */
    public String getFormattedAddress() {
        return formatted_address;
    }

}