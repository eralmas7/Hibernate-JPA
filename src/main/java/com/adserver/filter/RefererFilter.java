package com.adserver.filter;

import java.util.LinkedList;
import java.util.List;
import com.adserver.datatype.AdDBResponse;

/**
 * Based on referral criteria, this class with filter the records.
 */
public class RefererFilter implements FilterCriteria {

    @Override
    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses, int referer) {
        List<AdDBResponse> adResponses = new LinkedList<AdDBResponse>();
        for (AdDBResponse adResponse : adResponses) {
            if (adResponse.getCategoryId() == referer) {
                adResponses.add(adResponse);
            }
        }
        return adResponses;
    }
}
