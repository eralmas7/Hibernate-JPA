package com.adserver.controller;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.adserver.entities.AdCustomer;
import com.adserver.exception.RowNotFoundException;
import com.adserver.service.ConfigService;
import com.adserver.utils.AdServerConstants;

@WebService(serviceName = AdServerConstants.AD_SERVER_CONFIG_SERVICE)
public class ConfigServiceController extends SpringBeanAutowiringSupport {

    private ConfigService configService;

    public ConfigServiceController() {}

    @Autowired
    public ConfigServiceController(final ConfigService configService) {
        this.configService = configService;
    }

    @WebMethod
    public AdCustomer updateAdCustomerData(@WebParam final AdCustomer adCustomer) throws RowNotFoundException {
        return configService.updateCustomerConfig(adCustomer);
    }

    @WebMethod
    public AdCustomer getCustomerData(@WebParam final int customerId) throws RowNotFoundException {
        return configService.getCustomerConfig(customerId);
    }
}
