package com.adserver.dao;

import com.adserver.entities.AdCustomer;
import com.adserver.exception.RowNotFoundException;

/**
 * Dao that will get/update the customer config changes from the backend.
 */
public interface CustomerConfigDao {

    /**
     * Updates the customer config and returns back the updated config to the client.
     * 
     * @param adCustomer
     * @return
     * @throws RowNotFoundException
     */
    public AdCustomer updateCustomer(AdCustomer adCustomer) throws RowNotFoundException;

    /**
     * Returns the customer configuration given the customer id.
     * 
     * @param customerId
     * @return
     * @throws RowNotFoundException
     */
    public AdCustomer getCustomer(int customerId) throws RowNotFoundException;
}
