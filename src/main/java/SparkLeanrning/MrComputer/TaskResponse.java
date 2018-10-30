/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.MrComputer;

/**
 * @author zhangdanfeng01
 *
 * 封装任务计算结果
 */
public class TaskResponse {

    private final String taskId;

    private  final JobState jobState;


    private final ResponseData<?> data;

    public TaskResponse(String taskId, JobState jobState, ResponseData<?> data) {
        this.taskId = taskId;
        this.jobState = jobState;
        this.data = data;
    }

    public enum JobState {
        JOB_STATE_NOT_CHANGE, JOB_HOLD, JOB_SUCCESS
    }
}
