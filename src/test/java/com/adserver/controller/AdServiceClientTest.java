package com.adserver.controller;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import com.adserver.datatype.AdMedia;

public class AdServiceClientTest {

    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        final CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(null, -1), new UsernamePasswordCredentials("adserver", "adserver"));
        final HttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpComponentsClientHttpRequestFactory commons = new HttpComponentsClientHttpRequestFactory(httpClient);
        template = new RestTemplate(commons);
    }

    @Test
    public void testRealAdServiceWithValidRecord() {
        String url = "http://localhost:8080/AdServerSample/getAd/4";
        AdMedia adMedia = template.getForObject(url, AdMedia.class);
        Assert.assertNotNull(adMedia);
        Assert.assertEquals(adMedia.getTitle(), "test4");
    }

    @Test
    public void testRealAdServiceWithNonExistingRecord() {
        String url = "http://localhost:8080/AdServerSample/getAd/400";
        try {
            template.getForObject(url, AdMedia.class);
            Assert.fail("Did not expected to be here");
        } catch (Exception e) {
        }
    }
}
