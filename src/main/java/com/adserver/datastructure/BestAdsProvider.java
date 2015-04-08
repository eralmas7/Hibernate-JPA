package com.adserver.datastructure;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.adserver.datatype.AdDBResponse;
import com.adserver.entities.AdCategory;

/**
 * A strategy which will give clients the best possible ad that user might be interested in.
 */
public class BestAdsProvider {

    private CategoryGraph categoryGraph;
    public static final String VISITED_AD_SEPERATOR = "\\s*,\\s*";

    public BestAdsProvider(final CategoryGraph categoryGraph) {
        this.categoryGraph = categoryGraph;
    }

    /**
     * This method will return the best possible ad's based on user's like what he wish to see and
     * what he has already been exploring.
     * 
     * @param inputAds
     * @param visitedAds
     * @return
     */
    public AdDBResponse getBestAdForClient(final List<AdDBResponse> inputAds, final String visitedAds) {
        final TreeMap<String, Integer> categoryVisitedCount = countUserLikes(visitedAds);
        if (categoryVisitedCount.isEmpty()) {
            return inputAds.get(0);
        }
        final int mostUserLikes = Integer.parseInt(categoryVisitedCount.firstKey());
        List<AdCategory> adCategories;
        for (AdDBResponse adDbResponse : inputAds) {
            adCategories = categoryGraph.getDescendantCategories(adDbResponse.getCategoryId());
            if (doesListContainsCategory(adCategories, mostUserLikes)) {
                return adDbResponse;
            }
        }
        return inputAds.get(0);// default none seems to be visited by user
    }

    private boolean doesListContainsCategory(final List<AdCategory> adCategories, final int mostUserLikes) {
        for (AdCategory adCategory : adCategories) {
            if (adCategory.getCategoryId() == mostUserLikes) {
                return true;
            }
        }
        return false;
    }

    private TreeMap<String, Integer> countUserLikes(final String visitedAds) {
        final Map<String, Integer> originalMap = new HashMap<String, Integer>();
        final ValueComparator valueComparator = new ValueComparator(originalMap);
        final TreeMap<String, Integer> sortedMapByCategoryVisitedCount = new TreeMap<String, Integer>(valueComparator);
        if (visitedAds.trim().equals("")) {
            return sortedMapByCategoryVisitedCount;
        }
        final String[] adsVisitedByClient = visitedAds.split(VISITED_AD_SEPERATOR);
        Integer value;
        for (String adVisitedByClient : adsVisitedByClient) {
            value = originalMap.get(adVisitedByClient);
            if (value == null) {
                originalMap.put(adVisitedByClient, 1);
            } else {
                originalMap.put(adVisitedByClient, value + 1);
            }
        }
        sortedMapByCategoryVisitedCount.putAll(originalMap);
        return sortedMapByCategoryVisitedCount;
    }

    private static class ValueComparator implements Comparator<String> {

        Map<String, Integer> baseMap;

        public ValueComparator(Map<String, Integer> baseMap) {
            this.baseMap = baseMap;
        }

        public int compare(String a, String b) {
            if (baseMap.get(a) >= baseMap.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
