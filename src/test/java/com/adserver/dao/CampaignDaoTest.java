package com.adserver.dao;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class CampaignDaoTest {

    private static ApplicationContext context;
    private TupleDao campaignDao;

    @BeforeClass
    public static void initContext() throws IOException {
        context = new FileSystemXmlApplicationContext(new File("src/main/webapp/WEB-INF/adserver_hibernate.xml").getCanonicalPath());
    }

    @Before
    public void setUp() throws Exception {
        campaignDao = context.getBean("campaignDao", TupleDao.class);
    }

    @Test
    public void testDaoForExistingRecord() {
        campaignDao.fetchAllRecords();
    }
}
