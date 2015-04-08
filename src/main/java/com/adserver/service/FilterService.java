package com.adserver.service;

import java.util.List;
import com.adserver.datatype.AdDBResponse;
import com.adserver.filter.FilterCriteria;

public class FilterService {

    private FilterCriteria categoryFilter;
    private FilterCriteria dailyLimitCampaignCriteria;
    private FilterCriteria refererCriteria;
    private FilterCriteria nullCriteria;

    public FilterService(FilterCriteria categoryFilter, FilterCriteria dailyLimitCampaignCriteria, FilterCriteria refererCriteria, FilterCriteria nullCriteria) {
        this.categoryFilter = categoryFilter;
        this.dailyLimitCampaignCriteria = dailyLimitCampaignCriteria;
        this.refererCriteria = refererCriteria;
        this.nullCriteria = nullCriteria;
        createFilterChain();
    }

    private void createFilterChain() {
        categoryFilter.addNextFilter(nullCriteria);
        dailyLimitCampaignCriteria.addNextFilter(categoryFilter);
        refererCriteria.addNextFilter(dailyLimitCampaignCriteria);
    }

    public List<AdDBResponse> filterAds(List<AdDBResponse> adDbResponses, int referer) {
        return refererCriteria.meetCriteria(adDbResponses, referer);
    }
}
