package com.adserver.dao;

import com.adserver.entities.AdCustomer;
import com.adserver.exception.RowNotFoundException;

public interface CustomerConfigDao {

    public AdCustomer updateCustomer(AdCustomer adCustomer) throws RowNotFoundException;

    public AdCustomer getCustomer(int customerId) throws RowNotFoundException;
}
