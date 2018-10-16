/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package FiltterWords;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.Builder;

@Builder
public class FilterTool<T,R> implements Serializable {


    private Predicate<T> defauterPredicate=(k)->true;

    private Function<String,String> defaultlValueMapper =(k)->k;

    private List<Predicate<T>> filterGroup;//过滤器组


    private Collection<T> target; //待分析对象

    private Function<T,R> valueMapper;
    private int Mininst=6;


    private Predicate<T> beAdd=null;

    //定义算法骨架


    public  void filterWithPredicate(Collection<R> res){
        //根据过滤器组进行数据过滤
        if(beAdd!=null) filterGroup.add(beAdd);
        Predicate<T> finalPredicate= filterGroup.stream().reduce((s1,s2)->s1.and(s2)).get();
        List<R> tList=   target.stream().filter(u->finalPredicate.test(u)).map(value->valueMapper.apply(value)).collect
                (Collectors
                .toList());
        res.addAll(tList);

    }

    public FilterTool<String,String> filerTool(Collection<String> target,List<Predicate<String>> predicates){
        return filterTools(target,predicates, defaultlValueMapper);
    }

    public static <T,R> FilterTool<T,R> filterTools(Collection<T> target, List<Predicate<T>>
            filerGroup, Function<T,R> valueMapper){

        return FilterTool.<T, R>builder()
                .filterGroup(filerGroup)
                .target(target)
                .valueMapper(valueMapper)
                .build();
    }


    public static void main(String [] args){

        List<String> word= Arrays.asList("hello,weod","helllllllll","reueru","呵呵哒呵呵哒很多很多","华为华为华为忘记就忘记我");

        Predicate<String> predicate1=(k)->k.length()>5;
        Predicate<String> predicate2=(k)->k.contains("h");
        Collection<String> res=new ArrayList<>();
        FilterTool<String,String> filterTool= filterTools(word,Arrays.asList(predicate1,predicate2),(v)->v.toLowerCase());
        filterTool.filterWithPredicate(res);
        res.stream().forEach(s-> System.out.println(s));


    }
}
