package com.adserver.dao;

import com.adserver.entities.AdCustomer;
import com.adserver.exception.RowNotFoundException;

public class CustomerConfigDaoImpl extends AbstractDaoBase implements CustomerConfigDao {

    public AdCustomer updateCustomer(AdCustomer adCustomer) throws RowNotFoundException {
        final AdCustomer staleAdCustomer = getEntityManager().find(AdCustomer.class, adCustomer.getCustomerId());
        if (staleAdCustomer == null) {
            throw new RowNotFoundException("Unable to find customer with record " + adCustomer);
        }
        try {
            getEntityManager().getTransaction().begin();
            staleAdCustomer.setCustomerDns(adCustomer.getCustomerDns());
            staleAdCustomer.setCustomerEndDate(adCustomer.getCustomerEndDate());
            staleAdCustomer.setCustomerStartDate(adCustomer.getCustomerStartDate());
            staleAdCustomer.setCustomerName(adCustomer.getCustomerName());
            staleAdCustomer.setCustomerType(adCustomer.getCustomerType());
            staleAdCustomer.setIsCustomerActive(adCustomer.getIsCustomerActive());
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
        }
        return staleAdCustomer;
    }

    public AdCustomer getCustomer(int customerId) throws RowNotFoundException {
        final AdCustomer dbAdCustomer = getEntityManager().find(AdCustomer.class, customerId);
        if (dbAdCustomer == null) {
            throw new RowNotFoundException("Unable to find customer with customer id " + customerId);
        }
        return dbAdCustomer;
    }
}
