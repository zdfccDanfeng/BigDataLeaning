/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning;

import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import com.baidu.cedp.scp.task.core.api.JobEnvironment;
import com.baidu.cedp.scp.task.extras.api.SparkSessionJob;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

//import org.apache.spark.ml.linalg.*;

public class ClusterText implements SparkSessionJob<String,String> {
    @Override
    public String run(SparkSession sparkSession, JobEnvironment jobEnvironment, String s) {


        String fileName="xx";

        JavaRDD<String> sentences=new JavaSparkContext(sparkSession.sparkContext())
                .textFile(fileName);
        JavaRDD<String> segRDD=sentences.map(new Seg());

        JavaRDD<Row> jrdd = segRDD.map(new StringtoRow());
        segRDD.cache();
        //数据转换为矩阵
        StructType schema = new StructType(new StructField[]{
                new StructField("sentence", DataTypes.StringType, false, Metadata.empty())
        });

        Dataset<Row> sentenceDataFrame=sparkSession.createDataFrame(jrdd,schema);

       // Tokenizer tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words");  //tokenizer以简单的空白分割词语
        //DataFrame wordsData = tokenizer.transform(sentenceData); // 将句子分割词语
        return "xxx";
    }

    /**
     * 分词器
      */
  static class Seg implements  Function<String, String> {

        @Override
        public String call(String sentence) throws Exception {

                Segment segment = HanLP.newSegment();
                String segStr = "";
                List<Term> termList = segment.seg(sentence); //分词
                StringBuilder sb = new StringBuilder();
                for(Term term: termList){
                    String word = term.word;
                    sb.append(word+ " ");
                }
                segStr = sb.toString().trim();
                return segStr;

        }
    }
    //将String的sentence转变为mllib中row数据类型
    static class StringtoRow implements Function<String, Row> {

        public Row call(String sentence) throws Exception {
            return RowFactory.create(sentence);
        }
    }
}
