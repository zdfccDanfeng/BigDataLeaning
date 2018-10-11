/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package 范型序列化;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GsonDemo {
    public static void main(String [] args) throws IOException {
        Gson gson=new Gson();
        List<String> arr=Arrays.asList("aa","bb","cc","dd");
        List<String> arr1=Arrays.asList("uu","vv","ii","GsonDemo");
        Models models=new Models(arr,"modle1");
        Models models2=new Models(arr1,"modle2");
        Collection<Models> modelsCollection=Arrays.asList(models,models2);

        String res=toJson(modelsCollection);
        System.out.println(res);

        Collection<Models> tt=jsonStringConvertToList(res,Models[].class);
        Collection<Models> rr=parseJson(res,Models.class);

        System.out.println(rr);

        System.out.println(tt);

        //测试 Gson泛型序列化的能力
        System.out.println("hhaha");
//

        String s="[288282,92939924,49949943]";
        JsonElement jsonObject=new JsonParser().parse(s);
        Type type=new TypeToken<RktSkuInfo>(){}.getType();
        System.out.println(jsonObject.toString());
      Collection<Integer> rktSkuInfos= parseJson(jsonObject.getAsJsonArray().toString(),
        Integer.class);
        System.out.println(rktSkuInfos);
        System.out.println(jsonObject.getAsJsonArray().toString());
        Type type1=new TypeToken<Integer>(){}.getType();
       //System.out.println(gson.fromJson(jsonObject.getAsJsonArray(),type).toString());
//  List<String> ll=Arrays.asList("11111","2323434");
//
//        System.out.println(""+toJson(ll)+"");
//        System.out.println(parseJson(toJson(ll),String.class));
   }


    public static  <T> String toJson(Collection<T> collection){

        Type type=new TypeToken<Collection<T>>(){}.getType();

        return new Gson().toJson(collection,type);
    }

    public static <T> Collection<T> parseJson(String s,Class<T> cls) throws IOException {
        Collection<T> list=new ArrayList<>();
        Gson gson=new Gson();
        JsonArray array=new JsonParser().parse(s).getAsJsonArray();
        for (JsonElement jsonElement : array) {
            list.add(gson.fromJson(jsonElement, cls));
        }
        return list;
    }


    public static <T> Collection<T> jsonStringConvertToList(String string, Class<T[]> cls) {
        Gson gson = new Gson();
        T[] array = gson.fromJson(string, cls);
        return Arrays.asList(array);
    }
    static class Models{

        private List<String> strings;

        private String name;

        public List<String> getStrings() {
            return strings;
        }

        public void setStrings(List<String> strings) {
            this.strings = strings;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Models(List<String> strings, String name) {
            this.strings = strings;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Models{" +
                    "strings=" + strings +
                    ", name='" + name + '\'' +
                    '}';
        }
    }









    //    public static Set<String> getDataPaths(String pathPrefix, String start, String end) {
    ////        return getDaysBetween(start, end).stream().map(
    ////                s -> pathPrefix+s.substring(0,4)+"-"+s.substring(4,6)+"-"+s.substring(6)
    ////        ).collect(Collectors.toSet());
    //        return getDaysBetween(start, end).stream().map(pathPrefix::concat).collect(Collectors.toSet());
    //    }
    //
    //
    //
    //    public static List<String> getDaysBetween(String start, String end) {
    //        return getStrings(start, end, plusDayBasic(start, 1));
    //    }
    //
    //    public static String plusDayBasic(String day, int n) {
    //        return LocalDate.parse(day, DateTimeFormatter.BASIC_ISO_DATE).plusDays(n)
    //                .format(DateTimeFormatter.BASIC_ISO_DATE);
    //    }
    //
    //    public static List<String> getStrings(String start, String end, String s) {
    //        Preconditions.checkArgument(LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE)
    //                .compareTo(LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE)) <= 0);
    //        List<String> result = new ArrayList<>();
    //        while (!start.equals(end)) {
    //            result.add(start);
    //            start = s;
    //        }
    //        result.add(end);
    //        return result;
    //    }

    public static Set<String> getDataPaths(String pathPrefix, String start, String end) {
        return getDaysBetween(start, end)
                .stream()
                .map(s->bianhuan(pathPrefix,s))
                .collect(Collectors.toSet());
    }


    public static String bianhuan(String s1,String s){

        return s1+s.substring(0,4)+"-"+s.substring(4,6)+"-"+s.substring(6);
    }
    public static List<String> getDaysBetween(String start, String end) {
        Preconditions.checkArgument(LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE)
                .compareTo(LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE)) <= 0);
        List<String> result = new ArrayList<>();
        while (!start.equals(end)) {
            result.add(start);
            start = plusDayBasic(start, 1);
        }
        result.add(end);
        return result;
    }

    public static String plusDayBasic(String day, int n) {
        return LocalDate.parse(day, DateTimeFormatter.BASIC_ISO_DATE).plusDays(n)
                .format(DateTimeFormatter.BASIC_ISO_DATE);
    }


    class RktSkuInfo{
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

}

