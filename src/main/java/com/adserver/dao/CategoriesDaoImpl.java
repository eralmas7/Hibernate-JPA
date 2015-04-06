package com.adserver.dao;

import java.util.List;
import javax.persistence.Query;
import com.adserver.entities.AdCategory;

public class CategoriesDaoImpl extends AbstractDaoBase implements CategoriesDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<AdCategory> fetchAllCategory() {
        final Query query = getEntityManager().createQuery("SELECT ac from AdCategory ac", AdCategory.class);
        return query.getResultList();
    }
}
