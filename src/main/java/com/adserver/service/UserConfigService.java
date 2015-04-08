package com.adserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adserver.dao.CustomerConfigDao;
import com.adserver.entities.AdCustomer;
import com.adserver.exception.RowNotFoundException;

@Service("configService")
public class UserConfigService implements ConfigService {

    @Autowired
    private CustomerConfigDao customerConfigDao;

    @Override
    public AdCustomer updateCustomerConfig(final AdCustomer adCustomer) throws RowNotFoundException {
        return customerConfigDao.updateCustomer(adCustomer);
    }

    @Override
    public AdCustomer getCustomerConfig(final int customerId) throws RowNotFoundException {
        return customerConfigDao.getCustomer(customerId);
    }
}
