package com.adserver.dao;

import java.util.List;
import javax.persistence.Query;
import com.adserver.entities.AdCategory;

public class CategoriesDaoImpl extends AbstractDaoBase implements CategoriesDao {

    private static final String CATEGORY_FETCH_QUERY = "SELECT ac from AdCategory ac";

    @SuppressWarnings("unchecked")
    @Override
    public List<AdCategory> fetchAllCategory() {
        final Query query = getEntityManager().createQuery(CATEGORY_FETCH_QUERY, AdCategory.class);
        return query.getResultList();
    }
}
