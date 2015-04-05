package com.adserver.controller;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.adserver.entities.AdCustomer;
import com.adserver.service.ConfigService;

@WebService(serviceName = "configService")
public class ConfigServiceController extends SpringBeanAutowiringSupport {

    @Autowired
    private ConfigService configService;

    @WebMethod
    public String sayHello(@WebParam AdCustomer adCustomer) {
        return configService.sayHello();
    }
}
