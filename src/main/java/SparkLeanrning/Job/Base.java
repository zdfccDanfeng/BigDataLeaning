/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Job;

import org.apache.spark.sql.SparkSession;

public class Base {

    private SparkSession sparkSession;

    public SparkSession getSparkSession() {
        return sparkSession;
    }

    public void setSparkSession(SparkSession sparkSession) {
        this.sparkSession = SparkSession.builder().appName("test").master("local").getOrCreate();
    }
}
