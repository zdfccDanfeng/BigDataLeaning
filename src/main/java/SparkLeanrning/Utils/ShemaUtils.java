/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Utils;

import java.util.ArrayList;
import java.util.List;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;


/**
 *
 * 提供两种数据构造方式
 */
public class ShemaUtils<T> {

    /**
     *
     * 在不明确dataset的具体形态的前提下，完成dataSet的schema创建
      * @return
     */
   public static StructType getDataFrameFromDesc(List<String> desc){
       List<StructField> schema=new ArrayList<>();
       for(String s:desc){
           StructField structField=DataTypes.createStructField(s,DataTypes.StringType,true);
           schema.add(structField);
       }
      return DataTypes.createStructType(schema);
   }

}
