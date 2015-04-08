package com.adserver.filter;

import java.util.LinkedList;
import java.util.List;
import com.adserver.datatype.AdDBResponse;
import com.adserver.service.CacheService;

/**
 * Filter out any ads that has potentially exceeded the daily limit.
 */
public class DailyLimitCampaignFilter implements FilterCriteria {

    @Override
    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses, int referer) {
        List<AdDBResponse> adResponses = new LinkedList<AdDBResponse>();
        Integer currentHitCount;
        Integer maxHitCount;
        for (AdDBResponse adResponse : adDbResponses) {
            currentHitCount = CacheService.getCount(adResponse.getCampaignId());
            maxHitCount = CacheService.getMaxHitCount(adResponse.getCampaignId());
            if (currentHitCount == null || currentHitCount < maxHitCount) {
                adResponses.add(adResponse);
            }
        }
        return adResponses;
    }
}
