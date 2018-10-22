/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Utils;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import SparkLeanrning.Model.JobData;
import SparkLeanrning.Model.JobDataWrapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class PraseExtProperties {

    static final String SPARK_READ_BASE_OPTION_PATH="basePath";

    static final String NULL_CUID="\\N";

    @Slf4j
    @NoArgsConstructor
    public static class Loader{

        private SparkSession sparkSession;

        private JobDataWrapper jobdata;

        private Boolean verbose;

        private Column [] selectExpr;


        public Loader spark(SparkSession sparkSession){
            this.sparkSession=sparkSession;
            return this;
        }

        public Loader jobData(JobData jobData){
            this.jobdata=jobdata;
            return this;
        }

        public Loader selectExptr(Column ... selectExpr){
            this.selectExpr= Stream.of(selectExpr).filter(Objects::nonNull).toArray(Column[]::new);
            return this;
        }

        public Dataset<Row> loadData(){
            Objects.requireNonNull(sparkSession);// 必选参数
            Objects.requireNonNull(jobdata);

            final Dataset<Row> dataset=loadDatas(sparkSession,jobdata).where("");


            return null;
        }


    }

    public static Dataset<Row> loadDatas(SparkSession sparkSession,JobDataWrapper jobDataWrapper){

        String basePath="xxx";

        Path path=new Path(basePath,ofMonthsBetween("colx", jobDataWrapper.getBeginDate(), jobDataWrapper.getEndDate
                ()));

        Dataset<Row> queryKGData = sparkSession.read()
                .option(SPARK_READ_BASE_OPTION_PATH, basePath)
                .parquet(path.toString());

        return queryKGData;

    }

    private static String ofMonthsBetween(String colx, String begin, String end) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY_MM_DD");
        DateTime startDate = DateTime.parse(begin, formatter);
        DateTime endDate = DateTime.parse(end, formatter);
        return ofMonthsBetween(colx, startDate, endDate);
    }

    private static String ofMonthsBetween(String partName, DateTime begin, DateTime end) {

        if (end.compareTo(begin) < 0) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY_MM_DD");
            throw new IllegalArgumentException(String.format(
                    "Between begin[%s] and end[%s] days must greater than zero",
                    begin.toString(formatter),
                    end.toString(formatter)
            ));
        }

        return IntStream.rangeClosed(0, Months.monthsBetween(begin, end).getMonths())
                .boxed()
                .map(i -> begin.plusMonths(i).toString(DateTimeFormat.forPattern("yyyyMM")).concat("*"))
                .distinct()
                .collect(Collectors.joining(",", partName + "={", "}"));
    }
    }
