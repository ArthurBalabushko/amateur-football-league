package com.itacademy.database.repository;

import com.itacademy.database.config.TestConfiguration;
import com.itacademy.database.util.TestDataHelper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class BaseCase {

    @Autowired
    private TestDataHelper testDataHelper;

    @Before
    public void initDb() {
        testDataHelper.deleteTestData();
        testDataHelper.importTestData();
    }
}
