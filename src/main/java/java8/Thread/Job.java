/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package java8.Thread;

import java.util.concurrent.CountDownLatch;

import java8.Util.WorkUtils;
import java8.model.OutPutFile;

public class Job {
    int SINGLE_FILE_MAX=60000;
    OutPutFile outPutFile=WorkUtils.getOutPutFile();
    int count=outPutFile.getRows();
    String fileName=outPutFile.fileName;
    final String fileNameWithTimeStamp=fileName+"_"+"randomTime()";
    public void tacke() throws InterruptedException {
        int filecount=0;
        if(count>SINGLE_FILE_MAX){
             filecount=count/SINGLE_FILE_MAX
                    +(count%SINGLE_FILE_MAX!=0?1:0);
        }
        final CountDownLatch latch=new CountDownLatch(filecount);
        final Long userid=WorkUtils.getUserid();

        for (int i = 1; i <=filecount ; i++) {
            outPutFile.setPageNo(i);
            outPutFile.setPageSize(SINGLE_FILE_MAX);
            int index=i;
            GlobalThreadPool.threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //依次生成单个文件
                    WorkUtils.createOneOutPutFile();

                    latch.countDown();
                     ;
                }
            });
        }
        latch.await();
        //等待所有的任务计算完成后，进行汇总
        WorkUtils.createZipExport();
    }
}
