package com.adserver.service;

import java.util.List;
import com.adserver.dao.AdDao;
import com.adserver.datatype.AdDBResponse;

/**
 * Service which will serve ad's based on user input.
 */
public class AdDbService {

    private AdDao adDao;

    public AdDbService(AdDao adDao) {
        this.adDao = adDao;
    }

    /**
     * Serves ad based on user input.
     * 
     * @param adSpace
     * @return
     */
    public List<AdDBResponse> fetchAllAds(String adSpace) {
        return adDao.fetchAds(adSpace);
    }
}
