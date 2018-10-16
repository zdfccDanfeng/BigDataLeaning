/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package ML.Tools;

import org.apache.commons.lang3.StringUtils;

/**
 *

 */
public class GetDistanceBetweenString {

    /**
     *  莱文斯坦距离(编辑距离)
     *
     *   莱文斯坦距离，又称Levenshtein距离，是编辑距离（edit distance）的一种。
     *   指两个字串之间，由一个转成另一个所需的最少编辑操作次数。
     *   许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int LevenshteinDistance(String s1,String s2){


        if(StringUtils.isEmpty(s1)||StringUtils.isEmpty(s2)){
            throw new IllegalArgumentException("string must not be null");
        }

        int n= s1.length();
        int m=s2.length();

        if(n==0) return m;
        else if(m==0) return n;

        if(n>m){
            //make sure n<=m
            String tmp=s1;
            s1=s2;
            s2= tmp;
            n=m;
            m=s2.length();
        }

        int p[]=new int[n+1];
        int d[]=new int[m+1];

        int _d[];
        int i,j,t_j;
        int cost;
        for (i = 0; i <= n; i++) {
            p[i] = i;
        }
        for (j = 1; j <= m; j++) {
            t_j = s2.charAt(j - 1);
            d[0] = j;
            for (i = 1; i <= n; i++) {
                cost = s1.charAt(i - 1) == t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }
            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }
        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }

    public static double getJaroWinklerDistance(CharSequence first, final CharSequence second) {
       return StringUtils.getJaroWinklerDistance(first, second);
    }

}
