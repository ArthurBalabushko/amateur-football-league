package com.itacademy.database.dao;

import com.itacademy.database.util.TestDataHelper;
import org.junit.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void initDb() {
        TestDataHelper.getInstance().deleteTestData();
        TestDataHelper.getInstance().importTestData();
    }
}
