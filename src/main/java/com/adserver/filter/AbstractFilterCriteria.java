package com.adserver.filter;

import java.util.List;
import com.adserver.datatype.AdDBResponse;

/**
 * Defines the criteria to filter out ad's that user might not be interested in.
 */
public abstract class AbstractFilterCriteria implements FilterCriteria {

    private FilterCriteria nextFilterCriteria;

    /**
     * This method will need next filter to apply.
     * 
     * @param filterCriteria
     */
    @Override
    public void addNextFilter(FilterCriteria nextFilterCriteria) {
        this.nextFilterCriteria = nextFilterCriteria;
    }

    protected FilterCriteria getNextFilterCriteria() {
        return nextFilterCriteria;
    }

    /**
     * Filter out the result and move with the next one if it exists.
     */
    @Override
    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses, int referer) {
        return nextFilterCriteria.meetCriteria(adDbResponses, referer);
    }
}
