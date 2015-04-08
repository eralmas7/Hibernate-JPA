package com.adserver.filter;

import java.util.LinkedList;
import java.util.List;
import com.adserver.datastructure.CategoryGraph;
import com.adserver.datatype.AdDBResponse;
import com.adserver.entities.AdCategory;

/**
 * Filter based on category that user might be interested in.
 */
public class CategoryFilter implements FilterCriteria {

    private CategoryGraph categoryGraph;

    public CategoryFilter(CategoryGraph categoryGraph) {
        this.categoryGraph = categoryGraph;
    }

    @Override
    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses, int referer) {
        List<AdDBResponse> adResponses = new LinkedList<AdDBResponse>();
        List<AdCategory> adCategories;
        for (AdDBResponse adResponse : adResponses) {
            adCategories = this.categoryGraph.getDescendantCategories(adResponse.getCategoryId());
            for (AdCategory adCategory : adCategories) {
                if (adCategory.getCategoryId() == adResponse.getCategoryId()) {
                    adResponses.add(adResponse);
                }
            }
        }
        return adResponses;
    }
}
