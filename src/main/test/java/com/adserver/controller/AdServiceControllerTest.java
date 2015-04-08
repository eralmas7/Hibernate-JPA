package com.adserver.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.adserver.datastructure.BestAdsProvider;
import com.adserver.datatype.AdDBResponse;
import com.adserver.datatype.AdMedia;
import com.adserver.exception.NoRecordFoundException;
import com.adserver.service.AdDbService;
import com.adserver.service.FilterService;

public class AdServiceControllerTest {

    private AdDbService adDbService;
    private FilterService filterService;
    private BestAdsProvider bestAdsProvider;
    private AdServiceController adServiceController;
    private HttpServletResponse httpServletResponse;

    @Before
    public void setup() throws Exception {
        bestAdsProvider = Mockito.mock(BestAdsProvider.class);
        filterService = Mockito.mock(FilterService.class);
        adDbService = Mockito.mock(AdDbService.class);
        httpServletResponse = Mockito.mock(HttpServletResponse.class);
        adServiceController = new AdServiceController(adDbService, filterService, bestAdsProvider);
    }

    @Test
    public void testControllerSuccessfully() {
        List<AdDBResponse> dbResponse = new ArrayList<AdDBResponse>();
        AdDBResponse adResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adResponse.getLocation()).thenReturn("src/main/test/resources/tomcat.png");
        dbResponse.add(adResponse);
        Mockito.when(adDbService.fetchAllAds("1")).thenReturn(dbResponse);
        Mockito.when(filterService.meetCriteria(dbResponse, 2)).thenReturn(dbResponse);
        Mockito.when(bestAdsProvider.getBestAdForClient(dbResponse, "1")).thenReturn(adResponse);
        try {
            AdMedia adMedia = adServiceController.getAdMediaForClient("1", 2, "1", httpServletResponse);
            Assert.assertNotNull(adMedia);
        } catch (Exception exception) {
            Assert.fail("Did not expected exception");
        }
    }

    @Test
    public void testControllerWithoutDataFromDBSuccessfully() {
        List<AdDBResponse> dbResponse = new ArrayList<AdDBResponse>();
        Mockito.when(adDbService.fetchAllAds("1")).thenReturn(dbResponse);
        Mockito.when(filterService.meetCriteria(dbResponse, 2)).thenReturn(dbResponse);
        try {
            adServiceController.getAdMediaForClient("1", 2, "1", httpServletResponse);
            Assert.fail("expected exception!!");
        } catch (NoRecordFoundException exception) {
        } catch (IOException exception) {
            Assert.fail("Did not expected exception");
        }
    }

    @Test
    public void testControllerWithoutDataFromFilterSuccessfully() {
        List<AdDBResponse> dbResponse = new ArrayList<AdDBResponse>();
        AdDBResponse adResponse = Mockito.mock(AdDBResponse.class);
        dbResponse.add(adResponse);
        List<AdDBResponse> filteredDbResponse = new ArrayList<AdDBResponse>();
        Mockito.when(adDbService.fetchAllAds("1")).thenReturn(dbResponse);
        Mockito.when(filterService.meetCriteria(dbResponse, 2)).thenReturn(filteredDbResponse);
        try {
            adServiceController.getAdMediaForClient("1", 2, "1", httpServletResponse);
            Assert.fail("expected exception!!");
        } catch (NoRecordFoundException exception) {
        } catch (IOException exception) {
            Assert.fail("Did not expected exception");
        }
    }
}
