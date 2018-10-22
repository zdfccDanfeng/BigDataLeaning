/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Job;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

import com.baidu.cedp.scp.task.core.api.JobEnvironment;
import com.baidu.cedp.scp.task.extras.api.SparkSessionJob;

import SparkLeanrning.Utils.ShemaUtils;
import java8.model.Resource;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class GroupTest implements SparkSessionJob<String,String> {

    public void testGroup(){
        StructType schema1=ShemaUtils.getDataFrameFromDesc(Arrays.asList("cuid","label","id"));

        StructType peopleSchema=ShemaUtils.getDataFrameFromDesc(Arrays.asList("cuid"));

        SparkSession sparkSession=SparkSession.builder().master("local").getOrCreate();
        Resource resource1=new Resource("1",1,"vv");
        Resource resource2=new Resource("2",2,"pp");
        Resource resource3=new Resource("3",3,"cc");
        Resource resource4=new Resource("1",4,"dd");
        Resource resource5=new Resource("2",5,"vv");
        Resource resource6=new Resource("3",6,"pp");

        List<Resource> resources=Arrays.asList(resource1,resource2,resource3,resource4,resource5,resource6);
        JavaSparkContext javaSparkContext=new JavaSparkContext(sparkSession.sparkContext());
        List<String> cuidList=Arrays.asList("1,2,3,5,6");
        System.out.println(sparkSession);
        //sparkSession.createDataset(cuidList,Encoders.STRING());
        JavaRDD<Resource> resourceJavaRDD= javaSparkContext.parallelize(resources);
//        Dataset<Row> rowDataset=sparkSession.createDataFrame(resourceJavaRDD,Resource.class);
        JavaRDD<Row> rowRDD=javaSparkContext.parallelize(cuidList).map((Function<String, Row>) v1 -> RowFactory.create
                (v1));
        Dataset<Resource> dataset=sparkSession.createDataset(resources,Encoders.bean(Resource.class));

        Dataset<Row> peopledataset=sparkSession.createDataFrame(rowRDD,peopleSchema);

        log.info("数据源的shema如下：");
        dataset.printSchema();
        log.info("人群的schema如下：");
        peopledataset.printSchema();

        dataset.show();
        peopledataset.show();

        System.out.println("==========================");
        dataset.join(peopledataset,"cuid").show();
    }

    @Override
    public String run(SparkSession sparkSession, JobEnvironment jobEnvironment, String s) {
        testGroup();
        return "xxxx";
    }
}
