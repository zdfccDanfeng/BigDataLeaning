/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.MrComputer;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.hive.HiveContext;

public class DefaultTaskContext extends SparkTaskContext implements TaskDesc<MyTaskDesc> {
    
    private final MyTaskDesc taskDesc;

    /**
     * 
     * 初始化任务描述和上下文执行环境。。
     * 
     * 
     * @param sparkContext
     * @param hiveContext
     * @param myTaskDesc
     */
    public DefaultTaskContext(SparkContext sparkContext,
                              HiveContext hiveContext,MyTaskDesc myTaskDesc) {
        super(sparkContext, hiveContext);
        this.taskDesc=myTaskDesc;
    }

    public DefaultTaskContext(SparkContext sparkContext) {
        super(sparkContext);
        taskDesc = getTaskDesc();
    }

    @Override
    public MyTaskDesc getTaskDesc() {
        return taskDesc;
    }
}
