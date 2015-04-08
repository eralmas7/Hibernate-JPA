package com.adserver.dao;

import java.util.List;
import com.adserver.entities.AdCategory;

/**
 * Dao to fetch all the categories that are meant for ads.
 */
public interface CategoriesDao {

    /**
     * Returns all the categories that are meant for ads.
     * 
     * @return
     */
    public List<AdCategory> fetchAllCategory();
}
