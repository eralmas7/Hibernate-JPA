package com.adserver.service;

import java.util.HashMap;
import java.util.Map;

public class CacheService {

    private final static Map<Integer, Integer> campaignHitCountMap = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> campaignMaxHitCountMap;
    private static Map<Integer, Integer> customerTypeMap;

    private CacheService() {}

    public static Integer getCount(final Integer campaignId) {
        return campaignHitCountMap.get(campaignId);
    }

    public static Integer getMaxHitCount(final Integer campaignId) {
        return campaignMaxHitCountMap.get(campaignId);
    }

    public static void incrementHitCount(final Integer campaignId) {
        Integer counter = campaignHitCountMap.get(campaignId);
        if (counter == null) {
            campaignHitCountMap.put(campaignId, 1);
        } else {
            campaignHitCountMap.put(campaignId, counter + 1);
        }
    }

    public static Integer getCustomerCategoryType(final Integer customerId) {
        return customerTypeMap.get(customerId);
    }

    public static void populateMaps(final Map<Integer, Integer> campaignMaxHitCountMap, final Map<Integer, Integer> customerTypeMap) {
        if (CacheService.customerTypeMap == null) { // no modification once initialized
            CacheService.campaignMaxHitCountMap = campaignMaxHitCountMap;
            CacheService.customerTypeMap = customerTypeMap;
        }
    }
}
