package com.adserver.dao;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.adserver.datatype.AdDBResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:adserver_hibernate.xml"})
public class AdRequestDaoTest {

    @Autowired
    private AdDao adDao;

    @Test
    public void testDaoForExistingRecord() {
        List<AdDBResponse> adList = adDao.fetchAds("4");
        Assert.assertNotNull(adList);
        Assert.assertEquals(1, adList.size());
        Assert.assertEquals(2, adList.get(0).getCampaignId());
        Assert.assertEquals("test4", adList.get(0).getTitle());
    }

    @Test
    public void testDaoForNonExistingRecord() {
        List<AdDBResponse> adList = adDao.fetchAds("100");
        Assert.assertNotNull(adList);
        Assert.assertEquals(0, adList.size());
    }
}
