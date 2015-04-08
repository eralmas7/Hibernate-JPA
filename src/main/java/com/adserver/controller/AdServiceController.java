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
import com.adserver.utils.AdServerConstants;
import com.google.common.io.Files;

/**
 * Controller which is meant for client that exposes rest call for them to get ad details.
 */
@Controller
public class AdServiceController {

    private final AdDbService adDbService;
    private final FilterService filterService;
    private final BestAdsProvider bestAdsProvider;

    @Autowired
    public AdServiceController(final AdDbService adDbService, final FilterService filterService, final BestAdsProvider bestAdsProvider) {
        this.adDbService = adDbService;
        this.filterService = filterService;
        this.bestAdsProvider = bestAdsProvider;
    }

    /**
     * Rest call for clients which will return the AdMedia in json format.
     * 
     * @param adSpace
     * @param referer
     * @param visitedAds
     * @param httpServletResponse
     * @return
     * @throws NoRecordFoundException
     * @throws IOException
     */
    @RequestMapping(value = AdServerConstants.AD_SERVICE_CONTROLLER_PATH, method = RequestMethod.GET, headers = AdServerConstants.AD_SERVICE_ENCODE_FORMAT)
    public @ResponseBody AdMedia getAdMediaForClient(@PathVariable final String adSpace, @RequestHeader(value = "referer", required = false, defaultValue = AdServerConstants.ONE) final Integer referer, @RequestHeader(value = AdServerConstants.AD_SERVICE_USER_VISIT_HISTORY_HEADER, required = false,
                    defaultValue = AdServerConstants.ONE) final String visitedAds, final HttpServletResponse httpServletResponse) throws NoRecordFoundException, IOException {
        final List<AdDBResponse> response = adDbService.fetchAllAds(adSpace);
        final List<AdDBResponse> filteredResponse = filterService.filterAds(response, referer);
        if (filteredResponse.size() == 0) {
            throw new NoRecordFoundException("No record found post applying filter for ad space " + adSpace);
        }
        final AdDBResponse responseToReturn = bestAdsProvider.getBestAdForClient(filteredResponse, visitedAds);
        httpServletResponse.setHeader(AdServerConstants.AD_SERVICE_USER_VISIT_HISTORY_HEADER, visitedAds + AdServerConstants.COMMA + responseToReturn.getCategoryId());
        return new AdMedia(responseToReturn.getTitle(), responseToReturn.getUrl(), Files.toByteArray(new File(responseToReturn.getLocation())));
    }
}
