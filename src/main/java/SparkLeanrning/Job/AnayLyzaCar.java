/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.baidu.cedp.scp.task.core.api.JobEnvironment;
import com.baidu.cedp.scp.task.extras.api.SparkSessionJob;

import SparkLeanrning.MockData.MockData;
import SparkLeanrning.Model.Key;
import SparkLeanrning.Model.KeyValuePair;
import SparkLeanrning.Model.Wise_KG;
import SparkLeanrning.Utils.PropertiesExerator;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 *
 *  统计分析汽车行业数据
 */

public class AnayLyzaCar  implements SparkSessionJob<String,String> {
    @Override
    public String run(SparkSession sparkSession, JobEnvironment jobEnvironment, String s) {

       Dataset<Row> dataset= sparkSession.createDataFrame(prepareKgData(),Wise_KG.class);
       dataset.printSchema();

        return "success";
    }















    public static List<Wise_KG> prepareKgData(){
        Wise_KG wise_kg=new Wise_KG("cuid1", 20181003, exProperties("brand", "宝马",
                "brand",
                "奔驰",
                "brand", "奥迪"
                ,"brand","宝马","dianchi","zhendeniubi","xx","langya"
        ), properties("brand","雪佛兰","shacha","冬季"),
                "3c",
                "phone",Intentions("cost,higth,want"));

        Wise_KG wise_kg1=new Wise_KG("cuid1", 20181004, exProperties("brand", "宝马",
                "brand",
                "奔驰",
                "brand", "奥迪"
        ), properties("brand","雪佛兰","kao","冬季","xuefo"),
                "3c",
                "phone",Intentions("higth,get"));

        Wise_KG wise_kg2=new Wise_KG("cuid1", 20181005, exProperties("brand", "宝马",
                "brand",
                "xuefo",
                "brand", "奥迪"
        ), properties("brand","ll","maoxian","oowo","xuefo","langya"),
                "3c",
                "phone",Intentions("cost,higth,get"));


       return MockData.prepareData(wise_kg,wise_kg1,wise_kg2);
    }

    public static Map<String,List<String>> properties(String... kv){

        Map<String,List<String>> map=new HashMap<>();
        if(kv==null||kv.length==0) return map;
        List<String> stringList=new ArrayList<>();
        for (int i = 1; i <kv.length ; i++) {
            stringList.add(kv[i]);
        }
        map.put(kv[0],stringList);
        return map;
    }

    public  static List<String> Intentions(String s){
        return Arrays.stream(s.split(",")).collect(Collectors.toList());
    }
    private static List<Map<String, String>> exProperties(String... kv) {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < kv.length; ) {
            Map<String, String> map = new HashMap<>();
            map.put(kv[i++], kv[i++]);
            list.add(map);
        }
        return list;
    }


    public static Dataset<Key> parseExproperties(Dataset<Row> ds, Set<String> keys){
        PropertiesExerator<String,String> exerator=PropertiesExerator.extractorOfkeys(keys);


        return ds.flatMap((FlatMapFunction<Row,Key>) row->{
            Set<String> brands=new HashSet<>();
            exerator.extractPropertiesFromExtProtiesNOkeys(row.getAs("extProperties"),brands);
            // exerator.extractPropertiesFromExtProti(row.getAs("extProperties"),brands);
            // exerator.extractFromProperties(row.getAs("properties"),brands);
            exerator.extractFromPropertiesWithoutKeys(row.getAs("properties"),brands);
            // System.out.println("............."+brands);
            //将properties和exproperties里面的值抽取到brands里面
            return brands.parallelStream().map(brand->
            {
                List<String> intentions= new ArrayList<>();
                // exerator.extractAllIntentions(row.getAs("intentions"),intentions);
                Key k=new Key();
                k.setBrand(brand);
                k.setCuid(row.getAs("cuid"));
                k.setPdate(row.getAs("event_day"));
                //row.getAs("intentions");
                //k.setIntentions(intentions);
                return k;
            }).iterator();
        },Encoders.bean(Key.class));
    }


    @Data
    @NoArgsConstructor
    class Classfity implements Serializable {
        private String key;

        private List<KeyValuePair<String,Integer>> valuePairs;
    }
}
