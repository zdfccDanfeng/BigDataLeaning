/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Utils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import SparkLeanrning.Model.KeyValuePair;
import SparkLeanrning.functons.BiFunctionS;
import SparkLeanrning.functons.BiPredicateS;
import SparkLeanrning.functons.FunctionS;
import lombok.Builder;
import scala.Option;
import scala.collection.JavaConverters;
import scala.collection.immutable.Map;
import scala.collection.mutable.WrappedArray;

/**
 *
 * 扩展属性抽取工具
 * @param <T>
 * @param <R>
 */
@Builder
public class PropertiesExerator<T,R> implements Serializable {


    private static BiFunctionS<String,String,String> defaultMapper=(k,v)->v;


    private FunctionS<T,String> keyExtra; //按照什么规则进行抽取

    private BiFunctionS<String,String,R> valueMapper; //数据转换变换规则

    private BiPredicateS<String,T> isAdd;   //过滤断言

    private Collection<T> target;  //抽取对象。参照

    /**
     * -- extProperties: array (nullable = true)
     *  |    |-- element: map (containsNull = true)
     *  |    |    |-- key: string
     *  |    |    |-- value: string (valueContainsNull = true)
     *
     *  定义算法骨架
     *
     * @param ext
     * @param result
     */
    public void extractPropertiesFromExtProties(WrappedArray<Map<String,String>> ext, Collection<R> result){

        if(ext==null) return;

        for (int i=0;i<ext.length();i++){

            Map<String,String> property=ext.apply(i);

            target.stream().forEach(t->{
                String key=keyExtra.call(t);
                // System.out.println("^^^^^^^^^^^^^^"+key);
                Option<String> pvalue= property.get(key);

                if (pvalue.isDefined()&&isAdd.test(pvalue.get(),t)){
                    result.add(valueMapper.call(key,pvalue.get().toLowerCase()));
                }

            });
        }
    }

    public void extractAllIntentions(WrappedArray<String> intentions,List<String> intentionList){
        for (int i = 0; i <intentions.length() ; i++) {
            intentionList.add(intentions.apply(i));
        }
    }

    public void extractPropertiesFromExtProtiesNOkeys(WrappedArray<Map<String,String>> ext, Collection<String> result){

        if(ext==null) return;

        for (int i=0;i<ext.length();i++){
            Map<String,String> property=ext.apply(i);
            // System.out.println(property.);
            Collection<String> tt= JavaConverters.asJavaCollectionConverter(property.keys()).asJavaCollection();
            result.addAll(tt);
        }
    }

    /**
     *  -- properties: map (nullable = true)
     *  |    |-- key: string
     *  |    |-- value: array (valueContainsNull = true)
     *  |    |    |-- element: string (containsNull = true)
     *
     * 定义算法骨架
     * @param properties
     * @param result
     */

    public void extractFromProperties(Map<String,WrappedArray<String>> properties,Collection<R> result){

        if(properties==null) return;

        target.stream().forEach(t->{
            String key=keyExtra.call(t);
            Option<WrappedArray<String>> pvalues= properties.get(key);
            if(pvalues.isDefined()){
                WrappedArray<String> values=pvalues.get();
                for (int i = 0; i <values.length() ; i++) {
                    if(isAdd.test(values.apply(i),t)){
                        result.add(valueMapper.call(key,values.apply(i).toLowerCase()));
                    }
                }
            }
        });
    }

    public void extractFromPropertiesWithoutKeys(Map<String,WrappedArray<String>> properties,Collection<String> result){

        if(properties==null) return;

        // Collection<String> keys= JavaConverters.asJavaCollectionConverter(properties.keys()).asJavaCollection();

        Collection<WrappedArray<String>> property=  JavaConverters.asJavaCollectionConverter(properties.values())
                .asJavaCollection();

        property.stream().forEach(stringWrappedArray -> {

            for (int i = 0; i <stringWrappedArray.length() ; i++) {
                result.add(properties.keys().head());
            }
        });

    }
    /**
     * 扩展属性解析器
     * exproties
     *
     * @param target
     * @return
     */
    public static PropertiesExerator<KeyValuePair<String,String>,String> extractOfPair
    (Collection<KeyValuePair<String,String
            >> target)
    {
        return extractFromPair(target,defaultMapper);

    }

    /**
     *
     *  扩展属性抽取器。
     * @param target
     * @param valueMapper
     * @return
     */
    private static <R> PropertiesExerator<KeyValuePair<String, String>, R> extractFromPair(
            Collection<KeyValuePair<String, String>> target, BiFunctionS<String, String, R> valueMapper) {
        return PropertiesExerator.<KeyValuePair<String, String>, R>builder()
                .keyExtra(KeyValuePair::getKey)
                .isAdd((value,kv)->value.equalsIgnoreCase(kv.getValue()))
                .target(target)
                .valueMapper(valueMapper)
                .build();
    }

    /**
     *  properties属性解析器
     * @param
     * @return
     */
    public static  PropertiesExerator<String,String> extractOfValues(String key,Set<String> values){

        return extractOfValues(key,values,defaultMapper);
    }

    /**
     * 基本属性propreties抽取器
     * @param key
     * @param values
     * @param valueMapper
     * @return
     */
    private static <R> PropertiesExerator<String, R>
    extractOfValues(String key, Set<String> values,
                    BiFunctionS<String, String,
                                                R> valueMapper) {
        return PropertiesExerator.<String, R>builder()
                .target(Arrays.asList(key))
                .keyExtra(k->k)
                .isAdd((v,k)->values.contains(v.toLowerCase()))
                .valueMapper(valueMapper)
                .build();
    }

    public static PropertiesExerator<String,String> extractorOfkeys(Collection<String> keys){
        return extractOfKeys(keys,defaultMapper);
    }

    private static <R> PropertiesExerator<String,R> extractOfKeys(
            Collection<String> keys,
            BiFunctionS<String,String,R>
                    valueMapper) {
        return PropertiesExerator.<String, R>builder()
                .isAdd((v,t)->true)
                .keyExtra(k->k)
                .target(keys)
                .valueMapper((k,v)-> (R) "")
                .build();
    }

}

