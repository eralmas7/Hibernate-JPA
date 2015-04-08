package com.adserver.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.adserver.datatype.AdDBResponse;
import com.adserver.entities.AdCategory;

public class BestAdsProviderTest {

    private BestAdsProvider bestAdsProvider;
    private CategoryGraph categoryGraph;

    @Before
    public void setUp() throws Exception {
        categoryGraph = Mockito.mock(CategoryGraph.class);
        bestAdsProvider = new BestAdsProvider(categoryGraph);
    }

    @Test
    public void testAdProviderSucessfully() {
        List<AdDBResponse> inputAds = new ArrayList<AdDBResponse>();
        AdDBResponse adDbResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adDbResponse.getCategoryId()).thenReturn(1);
        inputAds.add(adDbResponse);
        adDbResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adDbResponse.getCategoryId()).thenReturn(2);
        inputAds.add(adDbResponse);
        AdCategory adCategory = Mockito.mock(AdCategory.class);
        Mockito.when(adCategory.getCategoryId()).thenReturn(2);
        Mockito.when(categoryGraph.getDescendantCategories(2)).thenReturn(Arrays.asList(adCategory));
        adDbResponse = bestAdsProvider.getBestAdForClient(inputAds, "1,2,2,3");
        Assert.assertEquals(adDbResponse.getCategoryId(), 2);
    }

    @Test
    public void testAdProviderWithoutCategoryPresentForUserSucessfully() {
        List<AdDBResponse> inputAds = new ArrayList<AdDBResponse>();
        AdDBResponse adDbResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adDbResponse.getCategoryId()).thenReturn(1);
        inputAds.add(adDbResponse);
        adDbResponse = Mockito.mock(AdDBResponse.class);
        Mockito.when(adDbResponse.getCategoryId()).thenReturn(2);
        inputAds.add(adDbResponse);
        AdCategory adCategory = Mockito.mock(AdCategory.class);
        Mockito.when(adCategory.getCategoryId()).thenReturn(2);
        Mockito.when(categoryGraph.getDescendantCategories(2)).thenReturn(Arrays.asList(adCategory));
        adDbResponse = bestAdsProvider.getBestAdForClient(inputAds, "");
        Assert.assertEquals(adDbResponse.getCategoryId(), 1);
    }
}
