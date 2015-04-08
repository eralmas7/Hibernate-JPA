package com.adserver.filter;

import java.util.LinkedList;
import java.util.List;
import com.adserver.datatype.AdDBResponse;

/**
 * Based on referral criteria, this class with filter the records.
 */
public class RefererFilter extends AbstractFilterCriteria {

    @Override
    public List<AdDBResponse> meetCriteria(final List<AdDBResponse> adDbResponses, final int referer) {
        final List<AdDBResponse> adResponses = new LinkedList<AdDBResponse>();
        for (AdDBResponse adResponse : adResponses) {
            if (adResponse.getCategoryId() == referer) {
                adResponses.add(adResponse);
            }
        }
        return super.getNextFilterCriteria().meetCriteria(adDbResponses, referer);
    }
}
