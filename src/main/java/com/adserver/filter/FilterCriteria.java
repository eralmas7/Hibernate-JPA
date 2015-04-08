package com.adserver.filter;

import java.util.List;
import com.adserver.datatype.AdDBResponse;

/**
 * Defines the criteria to filter out ad's that user might not be interested in.
 */
public interface FilterCriteria {

    /**
     * This method is going to filter out records based on implementation and return the result post
     * applying the filter.
     * 
     * @param referer
     * @param adDbResponses
     * @return
     */
    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses, int referer);
}
