package com.adserver.service;

import com.adserver.entities.AdCustomer;
import com.adserver.exception.RowNotFoundException;

/**
 * Service to update/get customer configuration.
 */
public interface ConfigService {

    /**
     * Update's customer configuration.
     * 
     * @param adCustomer
     * @return
     * @throws RowNotFoundException
     */
    public AdCustomer updateCustomerConfig(AdCustomer adCustomer) throws RowNotFoundException;

    /**
     * fetches customer configuration based on id.
     * 
     * @param customerId
     * @return
     * @throws RowNotFoundException
     */
    public AdCustomer getCustomerConfig(int customerId) throws RowNotFoundException;
}
