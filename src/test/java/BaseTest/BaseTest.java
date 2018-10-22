/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package BaseTest;

import org.apache.spark.sql.SparkSession;
import org.junit.After;
import org.junit.Before;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseTest {


    public SparkSession sparkSession;

    public SparkSession getSparkSession() {
        return sparkSession;
    }

    @Before
    public void setSparkSession() {
        this.sparkSession = SparkSession.builder().master("local").getOrCreate();
    }
    @After
    public void close(){
        this.sparkSession.close();
    }


}
