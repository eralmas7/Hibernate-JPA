package com.adserver.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.adserver.datatype.AdDBResponse;
import com.adserver.filter.FilterCriteria;

public class FilterServiceTest {

    private FilterService filterService;
    private FilterCriteria categoryFilter;
    private FilterCriteria dailyLimitCampaignCriteria;
    private FilterCriteria refererCriteria;

    @Before
    public void setUp() throws Exception {
        refererCriteria = Mockito.mock(FilterCriteria.class);
        dailyLimitCampaignCriteria = Mockito.mock(FilterCriteria.class);
        categoryFilter = Mockito.mock(FilterCriteria.class);
        filterService = new FilterService(categoryFilter, dailyLimitCampaignCriteria, refererCriteria);
    }

    @Test
    public void testFilterServiceWithoutAnyFiltering() {
        int referer = 1;
        List<AdDBResponse> adDbResponses = new ArrayList<AdDBResponse>();
        AdDBResponse adDBResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adDBResponse.getCategoryId()).thenReturn(1);
        Mockito.when(refererCriteria.meetCriteria(adDbResponses, referer)).thenReturn(adDbResponses);
        Mockito.when(dailyLimitCampaignCriteria.meetCriteria(adDbResponses, referer)).thenReturn(adDbResponses);
        Mockito.when(categoryFilter.meetCriteria(adDbResponses, referer)).thenReturn(adDbResponses);
        adDbResponses.add(adDBResponse);
        Assert.assertEquals(filterService.meetCriteria(adDbResponses, 1), adDbResponses);
    }

    @Test
    public void testFilterServiceWithDailyFiltering() {
        int referer = 1;
        List<AdDBResponse> adDbResponses = new ArrayList<AdDBResponse>();
        AdDBResponse adDBResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adDBResponse.getCategoryId()).thenReturn(1);
        Mockito.when(refererCriteria.meetCriteria(adDbResponses, referer)).thenReturn(adDbResponses);
        Mockito.when(dailyLimitCampaignCriteria.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Mockito.when(categoryFilter.meetCriteria(adDbResponses, referer)).thenReturn(adDbResponses);
        adDbResponses.add(adDBResponse);
        Assert.assertEquals(0, filterService.meetCriteria(adDbResponses, 1).size());
    }

    @Test
    public void testFilterServiceWithReferalFiltering() {
        int referer = 1;
        List<AdDBResponse> adDbResponses = new ArrayList<AdDBResponse>();
        AdDBResponse adDBResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adDBResponse.getCategoryId()).thenReturn(1);
        Mockito.when(refererCriteria.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Mockito.when(dailyLimitCampaignCriteria.meetCriteria(adDbResponses, referer)).thenReturn(adDbResponses);
        Mockito.when(categoryFilter.meetCriteria(adDbResponses, referer)).thenReturn(adDbResponses);
        adDbResponses.add(adDBResponse);
        Assert.assertEquals(0, filterService.meetCriteria(adDbResponses, 1).size());
    }

    @Test
    public void testFilterServiceWithCategorialFiltering() {
        int referer = 1;
        List<AdDBResponse> adDbResponses = new ArrayList<AdDBResponse>();
        AdDBResponse adDBResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adDBResponse.getCategoryId()).thenReturn(1);
        Mockito.when(refererCriteria.meetCriteria(adDbResponses, referer)).thenReturn(adDbResponses);
        Mockito.when(dailyLimitCampaignCriteria.meetCriteria(adDbResponses, referer)).thenReturn(adDbResponses);
        Mockito.when(categoryFilter.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        adDbResponses.add(adDBResponse);
        Assert.assertEquals(0, filterService.meetCriteria(adDbResponses, 1).size());
    }

    @Test
    public void testFilterServiceWithAllFiltering() {
        int referer = 1;
        List<AdDBResponse> adDbResponses = new ArrayList<AdDBResponse>();
        AdDBResponse adDBResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adDBResponse.getCategoryId()).thenReturn(1);
        Mockito.when(refererCriteria.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Mockito.when(dailyLimitCampaignCriteria.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Mockito.when(categoryFilter.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        adDbResponses.add(adDBResponse);
        Assert.assertEquals(0, filterService.meetCriteria(adDbResponses, 1).size());
    }

    @Test
    public void testFilterServiceWithEmptyDbRecords() {
        int referer = 1;
        List<AdDBResponse> adDbResponses = new ArrayList<AdDBResponse>();
        Mockito.when(refererCriteria.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Mockito.when(dailyLimitCampaignCriteria.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Mockito.when(categoryFilter.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Assert.assertEquals(0, filterService.meetCriteria(adDbResponses, 1).size());
    }
}
