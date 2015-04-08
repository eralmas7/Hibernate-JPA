package com.adserver.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import com.adserver.entities.AdCustomer;
import com.adserver.utils.AdServerConstants;

/**
 * Dao to fetch the customer data.
 */
public class CustomerDaoImpl extends AbstractDaoBase implements TupleDao {

    public static final String CUSTOMER_ID = "customerId";
    public static final String CUSTOMER_TYPE = "customerType";

    /**
     * Will fetch all the active customer's if and type information which will help to filter out
     * ad's based on its type.
     */
    @Override
    public List<Tuple> fetchAllRecords() {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        final Root<AdCustomer> adCustomerRoot = criteriaQuery.from(AdCustomer.class);
        final ParameterExpression<Date> todaysDate = criteriaBuilder.parameter(Date.class);
        criteriaQuery.multiselect(adCustomerRoot.get(CUSTOMER_ID), adCustomerRoot.get(CUSTOMER_TYPE)).where(
                        criteriaBuilder.and(criteriaBuilder.equal(adCustomerRoot.get(AdServerConstants.IS_CUSTOMER_ACTIVE), 1), criteriaBuilder.or(criteriaBuilder.between(todaysDate, adCustomerRoot.<Date>get(AdServerConstants.CUSTOMER_START_DATE), adCustomerRoot
                                        .<Date>get(AdServerConstants.CUSTOMER_END_DATE)), criteriaBuilder.isNull(adCustomerRoot.<Date>get(AdServerConstants.CUSTOMER_END_DATE)))));
        final List<Tuple> tupleResult = getEntityManager().createQuery(criteriaQuery).setParameter(todaysDate, new Date(), TemporalType.DATE).getResultList();
        return tupleResult;
    }
}
