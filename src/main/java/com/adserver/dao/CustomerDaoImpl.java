package com.adserver.dao;

import java.sql.Date;
import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import com.adserver.entities.AdCustomer;

/**
 * Dao to fetch the customer data.
 */
public class CustomerDaoImpl extends AbstractDaoBase implements TupleDao {

    /**
     * Will fetch all the active customer's if and type information which will help to filter out
     * ad's based on its type.
     */
    @Override
    public List<Tuple> fetchAllRecords() {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        final Root<AdCustomer> adCustomerRoot = criteriaQuery.from(AdCustomer.class);
        ParameterExpression<Date> todaysDate = criteriaBuilder.parameter(Date.class);
        criteriaQuery.multiselect(adCustomerRoot.get("customerId"), adCustomerRoot.get("customerType")).where(
                        criteriaBuilder.and(criteriaBuilder.equal(adCustomerRoot.get("isCustomerActive"), 1), criteriaBuilder.between(todaysDate, adCustomerRoot.<Date>get("customerStartDate"), adCustomerRoot.<Date>get("customerEndDate"))));
        final List<Tuple> tupleResult = getEntityManager().createQuery(criteriaQuery).getResultList();
        return tupleResult;
    }
}
