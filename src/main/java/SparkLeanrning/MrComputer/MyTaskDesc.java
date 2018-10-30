/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.MrComputer;

public class MyTaskDesc {


    private String taskName;

    private String outputPath;


    private String outputFormat;


    private String sheduleWay;


    //""""

    public MyTaskDesc(String taskName, String outputPath, String outputFormat, String sheduleWay) {
        this.taskName = taskName;
        this.outputPath = outputPath;
        this.outputFormat = outputFormat;
        this.sheduleWay = sheduleWay;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getSheduleWay() {
        return sheduleWay;
    }

    public void setSheduleWay(String sheduleWay) {
        this.sheduleWay = sheduleWay;
    }
}
