/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

import com.google.common.collect.Lists;

import java8.model.Resource;
import scala.Tuple2;

public class GroupByTest {

    public static void main(String [] args){

        Resource resource1=Resource.builder().cuid("1").id(1).build();
        Resource resource2=Resource.builder().cuid("2").id(2).build();
        Resource resource3=Resource.builder().cuid("3").id(3).build();
        Resource resource4=Resource.builder().cuid("2").id(4).build();
        Resource resource5=Resource.builder().cuid("5").id(5).build();
        Resource resource6=Resource.builder().cuid("3").id(6).build();
        Resource resource7=Resource.builder().cuid("2").id(7).build();

        List<Resource> resourceList=Arrays.asList(resource1,resource2,resource3,resource4,resource5
        ,resource6,resource7);

        Map<String,List<Resource>> resourceByCuid=resourceList.stream()
                .collect(Collectors.groupingBy(Resource::getCuid,Collectors.toList()));

        List<Resource> resourceList1=resourceByCuid.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->{
            List<Resource> resources=  e.getValue().stream().filter(s->s.getCuid().length()>12).collect(Collectors
                    .toList());
            return resources.isEmpty()?e.getValue():resources;
        })).values().stream().flatMap(x->x.stream()).collect(Collectors.toList());



        System.out.println(resourceByCuid);
        JavaRDD<Resource> items = getItemsRDD();
        JavaPairRDD<String, Resource> itemsByName =
                items.mapToPair(e -> new Tuple2<String, Resource>(e.getCuid(), e));





    }

    private static JavaRDD<Resource> getItemsRDD() {
        return null;
    }
}
