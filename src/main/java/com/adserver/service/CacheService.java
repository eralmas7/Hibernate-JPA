package com.adserver.service;

import java.util.HashMap;
import java.util.Map;

public class CacheService {

    private final static Map<Integer, Integer> campaignHitCountMap = new HashMap<Integer, Integer>();
    private static Map<Integer, Integer> campaignMaxHitCountMap;
    private static Map<Integer, String> customerTypeMap;

    private CacheService() {}

    public static Integer getCount(Integer campaignId) {
        return campaignHitCountMap.get(campaignId);
    }

    public static Integer getMaxHitCount(Integer campaignId) {
        return campaignMaxHitCountMap.get(campaignId);
    }

    public static void incrementHitCount(Integer campaignId) {
        Integer counter = campaignHitCountMap.get(campaignId);
        if (counter == null) {
            campaignHitCountMap.put(campaignId, 1);
        } else {
            campaignHitCountMap.put(campaignId, counter + 1);
        }
    }

    public static String getCustomerType(Integer customerId) {
        return customerTypeMap.get(customerId);
    }

    public static void populateMaps(Map<Integer, Integer> campaignMaxHitCountMap, Map<Integer, String> customerTypeMap) {
        if (customerTypeMap == null) { // no modification once initialized
            CacheService.campaignMaxHitCountMap = campaignMaxHitCountMap;
            CacheService.customerTypeMap = customerTypeMap;
        }
    }
}
