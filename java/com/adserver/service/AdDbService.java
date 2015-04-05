package com.adserver.service;

import java.util.List;
import com.adserver.dao.AdDao;
import com.adserver.datatype.AdDBResponse;

public class AdDbService {

    private AdDao adDao;

    public AdDbService(AdDao adDao) {
        this.adDao = adDao;
    }

    public List<AdDBResponse> fetchAllAds(String adSpace) {
        return adDao.fetchAds(adSpace);
    }
}
