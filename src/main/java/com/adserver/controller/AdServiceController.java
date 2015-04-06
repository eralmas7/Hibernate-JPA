package com.adserver.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.adserver.datastructure.BestAdsProvider;
import com.adserver.datatype.AdDBResponse;
import com.adserver.datatype.AdMedia;
import com.adserver.exception.NoRecordFoundException;
import com.adserver.service.AdDbService;
import com.adserver.service.FilterService;
import com.google.common.io.Files;

@Controller
public class AdServiceController {

    @Autowired
    private AdDbService adDbService;
    @Autowired
    private FilterService filterService;
    @Autowired
    private BestAdsProvider bestAdsProvider;

    @RequestMapping(value = "/getAd/{adSpace}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody AdMedia hello(@PathVariable String adSpace, @RequestHeader(value = "referer", required = false, defaultValue = "1") final Integer referer, @RequestHeader(value = "lastViewed", required = false, defaultValue = "1") final String visitedAds,
                    HttpServletResponse httpServletResponse) throws NoRecordFoundException, IOException {
        final List<AdDBResponse> response = adDbService.fetchAllAds(adSpace);
        System.out.println("Response is " + response);
        filterService.getRefererCriteria().setReferer(referer);
        final List<AdDBResponse> filteredResponse = filterService.meetCriteria(response);
        System.out.println(response);
        if (filteredResponse.size() == 0) {
            throw new NoRecordFoundException("No record found post applying filter for ad space " + adSpace);
        }
        final AdDBResponse responseToReturn = bestAdsProvider.getBestAdForClient(filteredResponse, visitedAds);
        httpServletResponse.setHeader("lastViewed", visitedAds + "," + responseToReturn.getCategoryId());
        return new AdMedia(responseToReturn.getTitle(), responseToReturn.getUrl(), Files.toByteArray(new File(responseToReturn.getLocation())));
    }
}
