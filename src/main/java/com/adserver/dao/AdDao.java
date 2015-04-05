package com.adserver.dao;

import java.util.List;
import com.adserver.datatype.AdDBResponse;

public interface AdDao {

    public List<AdDBResponse> fetchAds(String adSpace);
}
