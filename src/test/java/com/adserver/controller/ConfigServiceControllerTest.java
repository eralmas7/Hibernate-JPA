package com.adserver.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.adserver.entities.AdCustomer;
import com.adserver.exception.RowNotFoundException;
import com.adserver.service.ConfigService;

public class ConfigServiceControllerTest {

    private ConfigServiceController configServiceController;
    private ConfigService configService;

    @Before
    public void setUp() throws Exception {
        configService = Mockito.mock(ConfigService.class);
        configServiceController = new ConfigServiceController(configService);
    }

    @Test
    public void testConfigControllerSuccessfully() {
        AdCustomer adCustomer = Mockito.mock(AdCustomer.class);
        try {
            configServiceController.updateAdCustomerData(adCustomer);
            configServiceController.getCustomerData(1);
        } catch (Exception e) {
            Assert.fail("Did not expected exception");
        }
    }

    @Test
    public void testConfigControllerOnExceptionFromGetService() {
        String message = "blah blah";
        try {
            Mockito.when(configService.getCustomerConfig(1)).thenThrow(new RowNotFoundException(message));
            configServiceController.getCustomerData(1);
            Assert.fail("Did not expected exception");
        } catch (RowNotFoundException e) {
            Assert.assertEquals(e.getMessage(), message);
        }
    }

    @Test
    public void testConfigControllerOnExceptionFromUpdateService() {
        String message = "blah blah";
        AdCustomer adCustomer = Mockito.mock(AdCustomer.class);
        try {
            Mockito.when(configService.updateCustomerConfig(adCustomer)).thenThrow(new RowNotFoundException(message));
            configServiceController.updateAdCustomerData(adCustomer);
            Assert.fail("Did not expected exception");
        } catch (RowNotFoundException e) {
            Assert.assertEquals(e.getMessage(), message);
        }
    }
}
