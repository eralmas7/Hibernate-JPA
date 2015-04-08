package com.adserver.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.adserver.datatype.AdDBResponse;
import com.adserver.filter.FilterCriteria;
import com.adserver.filter.NullFilter;

public class FilterServiceTest {

    private FilterService filterService;
    private FilterCriteria categoryFilter;
    private FilterCriteria dailyLimitCampaignCriteria;
    private FilterCriteria refererCriteria;
    private FilterCriteria nullCriteria;

    @Before
    public void setUp() throws Exception {
        refererCriteria = Mockito.mock(FilterCriteria.class);
        dailyLimitCampaignCriteria = Mockito.mock(FilterCriteria.class);
        categoryFilter = Mockito.mock(FilterCriteria.class);
        nullCriteria = Mockito.mock(NullFilter.class);
        filterService = new FilterService(categoryFilter, dailyLimitCampaignCriteria, refererCriteria, nullCriteria);
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
        Assert.assertEquals(filterService.filterAds(adDbResponses, 1), adDbResponses);
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
        Assert.assertEquals(0, filterService.filterAds(adDbResponses, 1).size());
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
        Assert.assertEquals(0, filterService.filterAds(adDbResponses, 1).size());
    }

    @Test
    public void testFilterServiceWithEmptyDbRecords() {
        int referer = 1;
        List<AdDBResponse> adDbResponses = new ArrayList<AdDBResponse>();
        Mockito.when(refererCriteria.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Mockito.when(dailyLimitCampaignCriteria.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Mockito.when(categoryFilter.meetCriteria(adDbResponses, referer)).thenReturn(new ArrayList<AdDBResponse>());
        Assert.assertEquals(0, filterService.filterAds(adDbResponses, 1).size());
    }
}
