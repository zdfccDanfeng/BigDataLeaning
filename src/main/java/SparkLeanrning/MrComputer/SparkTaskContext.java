/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.MrComputer;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.hive.HiveContext;

/**
 *
 */
public class SparkTaskContext implements TaskContext {

    /**
     * Spark上下文信息
     */
    private final SparkContext sparkContext;
    /**
     * Sql上下文信息
     */
    private final HiveContext hiveContext;

    public SparkTaskContext(SparkContext sparkContext, HiveContext hiveContext) {
        this.sparkContext = sparkContext;
        this.hiveContext = hiveContext;
    }

    public SparkTaskContext(SparkContext sparkContext) {
        this.sparkContext = sparkContext;
        this.hiveContext = new HiveContext(sparkContext);
    }

    public SparkContext getSparkContext() {
        return sparkContext;
    }

    public HiveContext getHiveContext() {
        return hiveContext;
    }
}
