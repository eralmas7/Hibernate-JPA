package com.adserver.dao;

import java.util.List;
import com.adserver.entities.AdCategory;

public interface CategoriesDao {
    
    public List<AdCategory> fetchAllCategory();

}
