package com.adserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Tuple;
import com.adserver.dao.CategoriesDao;
import com.adserver.dao.TupleDao;
import com.adserver.datastructure.CategoryGraph;
import com.adserver.entities.AdCategory;

/**
 * Initiator for ad service which will warm up internal caches.
 */
public class AdServerInitiator {

    private CategoriesDao categoriesDao;
    private CategoryGraph categoryGraph;
    private TupleDao campaignDao;
    private TupleDao customerDao;

    public AdServerInitiator(CategoriesDao categoriesDao, CategoryGraph categoryGraph) {
        this.categoriesDao = categoriesDao;
        this.categoryGraph = categoryGraph;
    }

    private Map<Integer, Integer> getCampaignMap(List<Tuple> tuples) {
        final Map<Integer, Integer> campaignLimitMap = new HashMap<Integer, Integer>();
        for (Tuple tuple : tuples) {
            campaignLimitMap.put(Integer.valueOf((String) tuple.get(0)), Integer.valueOf((String) tuple.get(1)));
        }
        return campaignLimitMap;
    }

    private Map<Integer, String> getCustomerTypeMap(List<Tuple> tuples) {
        final Map<Integer, String> customerTypeMap = new HashMap<Integer, String>();
        for (Tuple tuple : tuples) {
            customerTypeMap.put(Integer.valueOf((String) tuple.get(0)), (String) tuple.get(1));
        }
        return customerTypeMap;
    }

    /**
     * Warms up category graph, customer type cache, campaign and its limit cache.
     */
    public void initiateServer() {
        final List<AdCategory> adCategories = categoriesDao.fetchAllCategory();
        categoryGraph.initGraph(adCategories);
        final List<Tuple> campaigns = campaignDao.fetchAllRecords();
        final List<Tuple> customers = customerDao.fetchAllRecords();
        CacheService.populateMaps(getCampaignMap(campaigns), getCustomerTypeMap(customers));
    }
}
