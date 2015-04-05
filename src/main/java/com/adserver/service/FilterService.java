package com.adserver.service;

import java.util.List;
import com.adserver.datatype.AdDBResponse;

public class FilterService implements FilterCriteria {

    private FilterCriteria categoryFilter;
    private FilterCriteria dailyLimitCampaignCriteria;
    private FilterCriteria refererCriteria;

    public FilterService(FilterCriteria categoryFilter, FilterCriteria dailyLimitCampaignCriteria, FilterCriteria refererCriteria) {
        this.categoryFilter = categoryFilter;
        this.dailyLimitCampaignCriteria = dailyLimitCampaignCriteria;
        this.refererCriteria = refererCriteria;
    }

    @Override
    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses) {
        return refererCriteria.meetCriteria(dailyLimitCampaignCriteria.meetCriteria(categoryFilter.meetCriteria(adDbResponses)));
    }
}
