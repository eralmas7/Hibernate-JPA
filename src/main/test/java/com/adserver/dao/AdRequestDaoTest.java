package com.adserver.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.adserver.datatype.AdDBResponse;

// @RunWith...
public class AdRequestDaoTest {

    private static ApplicationContext context;
    private AdDao adDao;

    @BeforeClass
    public static void initContext() throws IOException {
        context = new FileSystemXmlApplicationContext(new File("src/main/webapp/WEB-INF/adserver_hibernate.xml").getCanonicalPath());
    }

    @Before
    public void setUp() throws Exception {
        adDao = context.getBean("adDao", AdDao.class);
    }

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
