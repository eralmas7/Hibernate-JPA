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

    public AdServerInitiator(final CategoriesDao categoriesDao, final CategoryGraph categoryGraph, final TupleDao campaignDao, final TupleDao customerDao) {
        this.categoriesDao = categoriesDao;
        this.categoryGraph = categoryGraph;
        this.campaignDao = campaignDao;
        this.customerDao = customerDao;
    }

    private Map<Integer, Integer> getCampaignMap(final List<Tuple> tuples) {
        final Map<Integer, Integer> campaignLimitMap = new HashMap<Integer, Integer>();
        for (Tuple tuple : tuples) {
            campaignLimitMap.put((Integer) tuple.get(0), (Integer) tuple.get(1));
        }
        return campaignLimitMap;
    }

    private Map<Integer, Integer> getCustomerTypeMap(final List<Tuple> tuples) {
        final Map<Integer, Integer> customerTypeMap = new HashMap<Integer, Integer>();
        for (Tuple tuple : tuples) {
            customerTypeMap.put((Integer) tuple.get(0), (Integer) tuple.get(1));
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
