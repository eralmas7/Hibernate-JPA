package com.adserver.datastructure;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.adserver.datatype.AdDBResponse;

public class BestAdsProvider {

    private CategoryGraph categoryGraph;

    public BestAdsProvider(CategoryGraph categoryGraph) {
        this.categoryGraph = categoryGraph;
    }

    public AdDBResponse getBestAdForClient(List<AdDBResponse> inputAds, String visitedAds) {
        TreeMap<String, Integer> categoryVisitedCount = countUserLikes(visitedAds);
        final Integer mostUserLikes = Integer.valueOf(categoryVisitedCount.firstKey());
        for (AdDBResponse adDbResponse : inputAds) {
            if (categoryGraph.getDecendantCategories(adDbResponse.getCategoryId()).contains(mostUserLikes)) {
                return adDbResponse;
            }
        }
        return inputAds.get(0);// default none seems to be visited by user
    }

    private TreeMap<String, Integer> countUserLikes(String visitedAds) {
        Map<String, Integer> originalMap = new HashMap<String, Integer>();
        String[] adsVisitedByClient = visitedAds.split("\\s+,\\s+");
        Integer value;
        for (String adVisitedByClient : adsVisitedByClient) {
            value = originalMap.get(adVisitedByClient);
            if (value == null) {
                originalMap.put(adVisitedByClient, 1);
            } else {
                originalMap.put(adVisitedByClient, value + 1);
            }
        }
        ValueComparator valueComparator = new ValueComparator(originalMap);
        final TreeMap<String, Integer> sortedMapByCategoryVisitedCount = new TreeMap<String, Integer>(valueComparator);
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
