/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.MrComputer;

/**
 *
 *
 *   顶层任务执行接口，封装任务执行的整个生命周期，
 *
 *
 * @param <T>  任务执行上下文，方便后期扩展多种计算，诸如SparkContext、HiveContext、
 */

public interface Task<T extends TaskContext> {

    /**
     * 设置上下文执行环境
     *
     * @param ctx
     */
    void setTaskConText(T ctx);

    /**
     * 获取上下文执行环境
     * @return
     */
    T getTaskConText();

    /**
     * 初始化计算环境
     *
     * 比如 实例化SparkContext或者HiveContext等。
     *
     */
    void  init();

    /**
     *
     * 任务执行
     */
    void execute();

    /**
     * 清理执行计算环境
     */
    void  cleanup();



}
