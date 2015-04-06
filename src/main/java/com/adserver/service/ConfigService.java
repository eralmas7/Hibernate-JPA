package com.adserver.service;

import com.adserver.entities.AdCustomer;
import com.adserver.exception.RowNotFoundException;

public interface ConfigService {

    public AdCustomer updateCustomerConfig(AdCustomer adCustomer) throws RowNotFoundException;

    public AdCustomer getCustomerConfig(int customerId) throws RowNotFoundException;
}
