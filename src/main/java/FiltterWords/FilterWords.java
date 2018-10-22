/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package FiltterWords;

import static FiltterWords.FilterTool.filterTools;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilterWords {

    /**
     *  xx
     *  xx1
     *  xxoowoxx1xx1xx1
     *  ....
     *  ....
     *
     *  ...
     *  xx2
     *  xx2dkwek
     *  xx2..we.,xx2

     *
     * @param args
     */
    public static void main(String [] args){

        String content=readfile("/Users/zhangdanfeng01/Desktop/newjia_dian3.txt");
         Collection<String> target= Arrays.stream(content.split("\n")).sorted().distinct().collect(Collectors.toList());
        // 基于文本相似性进行过滤
     //   FilterTool<String,String> filterTool=filterTools(target,(s)->s);
        List<Predicate<String>> predicates=new ArrayList<>();
       // FilterTool<String,String> filterTool=FilterTool.filterTools(target,);
       // filterTool.
        Predicate<String> limnit=s1->s1.length()>=6;
        Predicate<String> engilsh=k->{
            String s1= removeSign(k);
            StringBuilder stringBuilder=new StringBuilder();
            for (char e:s1.toCharArray()){
                if((e >= 'a' && e <= 'z')
                        || (e >= 'A' && e <= 'Z')  || (e >= '0' && e <= '9')){
                    stringBuilder.append(e);
                }
            }
            return s1.length()>stringBuilder.toString().length();
        };
        Predicate<String> ungly=ss->!(ss.contains("#")||ss.contains("$")|| ss.contains("@"));
        Predicate<String> fff=ss->ss.contains("电视")||ss.contains("冰箱")||ss.contains("洗衣机")
                ||ss.contains("电")||ss.contains("tv")||ss.contains("洗")||ss.contains("空调")
                ||ss.contains("电动")||ss.contains("电脑")||ss.contains("手机");
        predicates.add(limnit);
       // predicates.add(engilsh);

        predicates.add(ungly);
        FilterTool<String,String> filterTool=filterTools(target,predicates,k->k.toLowerCase());
       predicates.add(fff);
        Collection<String> res=new ArrayList<>();
        filterTool.filterWithPredicate(res);
        Collection<String> res1;
//        res1=res.stream().collect(Collectors.groupingBy(s->s.substring(2))).entrySet().stream().filter(g->g.getValue
//                ().size()>5).flatMap
//                (s->s
//                .getValue()
//                .subList(s.getValue().size()-5,s.getValue().size()-1).stream()).collect(Collectors.toList());
//        //随机采样5000个词条。
        //相似度聚类
      //  System.out.println(res.stream().count());

        writeToFile("/Users/zhangdanfeng01/Desktop/newjia_dian4.txt",res.stream().reduce((x1,x2)->x1+"\n"+x2).get());

    }


    public static String readfile(String path){
        File file =FileUtils.getFile(path);
        try {
            return   FileUtils.readFileToString(file,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }


    public static void writeToFile(String path,String data){
        File file =FileUtils.getFile(path);
        try {
            FileUtils.writeStringToFile(file,data,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 判断字符是否为汉字，数字和字母，
     * 因为对符号进行相似度比较没有实际意义，故符号不加入考虑范围。
     * */
    public static boolean charReg(char charValue) {
        return (charValue >= 0x4E00 && charValue <= 0X9FA5) || (charValue >= 'a' && charValue <= 'z')
                || (charValue >= 'A' && charValue <= 'Z')  || (charValue >= '0' && charValue <= '9');
    }


    /*
     * 求公共子串，采用动态规划算法。
     * 其不要求所求得的字符在所给的字符串中是连续的。
     *
     * */
    public static String longestCommonSubstring(String strA, String strB) {
        char[] chars_strA = strA.toCharArray();
        char[] chars_strB = strB.toCharArray();
        int m = chars_strA.length;
        int n = chars_strB.length;

        /*
         * 初始化矩阵数据,matrix[0][0]的值为0，
         * 如果字符数组chars_strA和chars_strB的对应位相同，则matrix[i][j]的值为左上角的值加1，
         * 否则，matrix[i][j]的值等于左上方最近两个位置的较大值，
         * 矩阵中其余各点的值为0.
         */
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (chars_strA[i - 1] == chars_strB[j - 1])
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                else
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
            }
        }
        /*
         * 矩阵中，如果matrix[m][n]的值不等于matrix[m-1][n]的值也不等于matrix[m][n-1]的值，
         * 则matrix[m][n]对应的字符为相似字符元，并将其存入result数组中。
         *
         */
        char[] result = new char[matrix[m][n]];
        int currentIndex = result.length - 1;
        while (matrix[m][n] != 0) {
            //System.out.println(n+"  m"+matrix.length+"  n"+matrix[0].length);
            if (matrix[m][n] == matrix[m][n - 1])
                n--;
            else if (matrix[m][n] == matrix[m - 1][n])
                m--;
            else {
                result[currentIndex] = chars_strA[m - 1];
                currentIndex--;
                n--;
                m--;
            }
        }
        return new String(result);
    }

    /*
     * 结果转换成百分比形式
     * */
    public static String similarityResult(double resule){
        return  NumberFormat.getPercentInstance(new Locale( "en ", "US ")).format(resule);
    }


    /*
     * 将字符串的所有数据依次写成一行
     * */
    public static String removeSign(String str) {
        StringBuffer sb = new StringBuffer();
        //遍历字符串str,如果是汉字数字或字母，则追加到ab上面
        for (char item : str.toCharArray())
            if (charReg(item)){
                sb.append(item);
            }
        return sb.toString();
    }



    /*
     * 计算相似度
     * */
    public static double SimilarDegree(String strA, String strB){
        String newStrA = removeSign(strA);
        String newStrB = removeSign(strB);
        //用较大的字符串长度作为分母，相似子串作为分子计算出字串相似度
        int temp = Math.max(newStrA.length(), newStrB.length());
        int temp2 = longestCommonSubstring(newStrA, newStrB).length();
        return temp2 * 1.0 / temp;
    }



}
