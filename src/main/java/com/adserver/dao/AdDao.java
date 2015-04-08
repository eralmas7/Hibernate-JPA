package com.adserver.dao;

import java.util.List;
import com.adserver.datatype.AdDBResponse;

/**
 * Dao to fetch the valid active ads given the ad space from the clients.
 */
public interface AdDao {

    /**
     * This method will fetch all valid ads based on adspace requested to it.
     * 
     * @param adSpace
     * @return
     */
    public List<AdDBResponse> fetchAds(final String adSpace);
}
