package com.adserver.filter;

import java.util.LinkedList;
import java.util.List;
import com.adserver.datatype.AdDBResponse;
import com.adserver.service.CacheService;

/**
 * Filter out any ads that has potentially exceeded the daily limit.
 */
public class DailyLimitCampaignFilter extends AbstractFilterCriteria {

    @Override
    public List<AdDBResponse> meetCriteria(final List<AdDBResponse> adDbResponses, final int referer) {
        final List<AdDBResponse> adResponses = new LinkedList<AdDBResponse>();
        Integer currentHitCount;
        Integer maxHitCount;
        for (AdDBResponse adResponse : adDbResponses) {
            currentHitCount = CacheService.getCount(adResponse.getCampaignId());
            maxHitCount = CacheService.getMaxHitCount(adResponse.getCampaignId());
            if (currentHitCount == null || currentHitCount < maxHitCount) {
                adResponses.add(adResponse);
            }
        }
        return super.getNextFilterCriteria().meetCriteria(adDbResponses, referer);
    }
}
