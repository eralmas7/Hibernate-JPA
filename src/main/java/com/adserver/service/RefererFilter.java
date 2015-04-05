package com.adserver.service;

import java.util.LinkedList;
import java.util.List;
import com.adserver.datatype.AdDBResponse;

public class RefererFilter implements FilterCriteria {

    private Integer referer;

    public RefererFilter(Integer referer) {
        this.referer = referer;
    }

    @Override
    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses) {
        List<AdDBResponse> adResponses = new LinkedList<AdDBResponse>();
        for (AdDBResponse adResponse : adResponses) {
            if (adResponse.getCategoryId() == this.referer) {
                adResponses.add(adResponse);
            }
        }
        return adResponses;
    }
}
