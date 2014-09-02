/**
 * 
 */
package com.platzerworld.google.places.result;

import com.google.api.client.util.Key;
import com.platzerworld.google.places.models.Photo;

import java.util.List;

/**
 * @author Giuseppe Mastroeni - aka: Kataklisma E-Mail: m.giuseppe@a2plab.com
 * 
 */
public class PhotoResult extends Result {

    @Key
    private List<Photo> photos;

    /*
     * (non-Javadoc)
     * 
     * @see com.a2plab.googleplaces.result.Result#getResults()
     */
    @Override
    public List<Photo> getResults() {
        return photos;
    }

}
