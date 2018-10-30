/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.MrComputer;

/**
 *
 * 报表基类，  提供基础性的默认实现，
 * 后期自定义BI可以，根据
 *  重写钩子方法，比如execute()方法。。
 *
 *
 *
 */
public class BaseReportTask implements Task<DefaultTaskContext> {



    @Override
    public void setTaskConText(DefaultTaskContext ctx) {

    }

    @Override
    public DefaultTaskContext getTaskConText() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void cleanup() {

    }
}
