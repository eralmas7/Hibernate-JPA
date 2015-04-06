package com.adserver.controller;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.adserver.entities.AdCustomer;
import com.adserver.exception.RowNotFoundException;
import com.adserver.service.ConfigService;

@WebService(serviceName = "configService")
public class ConfigServiceController extends SpringBeanAutowiringSupport {

    @Autowired
    private ConfigService configService;

    @WebMethod
    public AdCustomer updateAdCustomerData(@WebParam AdCustomer adCustomer) throws RowNotFoundException {
        return configService.updateCustomerConfig(adCustomer);
    }

    @WebMethod
    public AdCustomer getCustomerData(@WebParam int customerId) throws RowNotFoundException {
        return configService.getCustomerConfig(customerId);
    }
}
