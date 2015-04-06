package com.adserver.dao;

import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.adserver.entities.AdCustomer;

public class CustomerDaoImpl extends AbstractDaoBase implements TupleDao {

    @Override
    public List<Tuple> fetchAllRecords() {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        final Root<AdCustomer> adCustomerRoot = criteriaQuery.from(AdCustomer.class);
        criteriaQuery.multiselect(adCustomerRoot.get("customerId"), adCustomerRoot.get("customerType"));
        final List<Tuple> tupleResult = getEntityManager().createQuery(criteriaQuery).getResultList();
        return tupleResult;
    }
}
