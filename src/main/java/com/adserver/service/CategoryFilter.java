package com.adserver.service;

import java.util.LinkedList;
import java.util.List;
import com.adserver.datastructure.CategoryGraph;
import com.adserver.datatype.AdDBResponse;
import com.adserver.entities.AdCategory;

public class CategoryFilter implements FilterCriteria {

    private CategoryGraph categoryGraph;

    public CategoryFilter(CategoryGraph categoryGraph) {
        this.categoryGraph = categoryGraph;
    }

    @Override
    public List<AdDBResponse> meetCriteria(List<AdDBResponse> adDbResponses) {
        List<AdDBResponse> adResponses = new LinkedList<AdDBResponse>();
        List<AdCategory> adCategories;
        for (AdDBResponse adResponse : adResponses) {
            adCategories = this.categoryGraph.getDecendantCategories(adResponse.getCategoryId());
            for (AdCategory adCategory : adCategories) {
                if (adCategory.getCategoryId() == adResponse.getCategoryId()) {
                    adResponses.add(adResponse);
                }
            }
        }
        return adResponses;
    }
}
