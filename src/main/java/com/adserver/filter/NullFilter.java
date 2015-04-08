package com.adserver.filter;

import java.util.List;
import com.adserver.datatype.AdDBResponse;

/**
 * Filter based on category that user might be interested in.
 */
public class NullFilter extends AbstractFilterCriteria {

    @Override
    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses, int referer) {
        return adDbResponses;
    }
}
