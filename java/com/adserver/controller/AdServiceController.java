package com.adserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.adserver.datatype.AdDBResponse;
import com.adserver.datatype.AdMedia;
import com.adserver.service.AdDbService;

@Controller
public class AdServiceController {

    @Autowired
    private AdDbService adDbService;

    @RequestMapping(value = "/getAd/{adSpace}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody AdMedia hello(@PathVariable String adSpace, Model model) {
        model.addAttribute("adSpace", adSpace);
        final List<AdDBResponse> response = adDbService.fetchAllAds(adSpace);
        System.out.println(response);
        return new AdMedia("MyTitle", "http://localhost:8080/testWeb/hello", "HelloWorld".getBytes());
    }
}
