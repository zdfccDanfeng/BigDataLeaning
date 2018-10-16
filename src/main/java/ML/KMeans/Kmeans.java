/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package ML.KMeans;

import static FiltterWords.FilterWords.removeSign;
import static org.apache.commons.lang3.StringUtils.getJaroWinklerDistance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

/**
 *
 *  文本字符串K_means算法实现r
 *
 *
 *  分析算法流程：
 *
 *   1。分词
 *   2。计算文本的相似度
 *   3。kmeans算法进行迭代--得到最终的聚类结果。
 *
 */
@Data
@Builder
public class Kmeans {

    private ArrayList<String> alltagetTextStringCollection; //聚类对象集合

    public int totalNumber=0; //总共的点文本字符串的数量

    public int K=0;

    /**
     * 随机初始化聚类中心  从0---totalNumber之间，随机选择k个点进行分析
     * @return
     */
    public Set<Integer> firstRandom(){

        Set<Integer> center=new HashSet<Integer>();

        Random ran=new Random();

        int roll=ran.nextInt(totalNumber);

        while (center.size()<K){
            roll=ran.nextInt(totalNumber);
            center.add(roll);
        }
        return center;
    }

    // 根据聚类中心初始化聚类信息
    public ArrayList<Cluster> init(Set<Integer> center) {
        ArrayList<Cluster> cluster = new ArrayList<Cluster>();// 聚类 的数组
        Iterator<Integer> it = center.iterator();
        while (it.hasNext()) {
            Cluster c = new Cluster();// 代表一个聚类
            c.setCenter(it.next());
            cluster.add(c);
        }
        return cluster;
    }



    /**
     *
     * 根据文本相似性对文本进行聚类算法
     *
     * @param center  聚类中心，用于计算各个文本字符串到中心点到距离，
     * @param clusters  聚类数组，用来将文本字符串进行聚类分析
     * @return
     */
    public ArrayList<Cluster> clusterTextString(Set<Integer> center,
                                                ArrayList<Cluster> clusters){

        ArrayList<Distance> distances=new ArrayList<Distance>();//存放距离信息，表示每个点到各个中心点的距离组成的数组


        String source=null;
        String dest=null;

        int id=0,id2=0;

       Object[] p= center.toArray();//p 为聚类中心点id数组

        boolean flag=false;

        for (int i = 0; i <totalNumber ; i++) {
            //每个点算完，并且聚类到距离最小的中心节点后，就清空距离数组
            distances.clear();

            for (int j = 0; j <center.size() ; j++) {

                //遍历中心节点，分别计算各个点到中心节点的距离

                if(!(center.contains(i))){ //如果该点没有归属于某个聚类中心

                    flag=true;

                    source=alltagetTextStringCollection.get(i);

                    dest=alltagetTextStringCollection.get((Integer) p[j]);//模板聚类中心

                    //计算距离并加入数组
                    CharSequence s1=removeSign(source);
                    CharSequence s2=removeSign(dest);
                    distances.add(new Distance((Integer) p[j],i,getJaroWinklerDistance(s1,s2)));
                }else {
                    flag=false;
                }
            }
            // 说明计算完某个文本字符串到类中心的距离,开始比较
            if (flag == true) {
                // 排序比较一个点到各个中心的距离的大小,找到距离最小的文本字符串的 目的id,和源id,
                // 目的id即类中心点id，这个就归到这个中心点所在聚类中
                double min = distances.get(0).getDist();// 默认第一个distance距离是最小的
                // 从1开始遍历distance数组
                int minid = 0;
                for (int k = 1; k < distances.size(); k++) {
                    if (min > distances.get(k).getDist()) {
                        min = distances.get(k).getDist();
                        id = distances.get(k).getDesc();// 目的，即类中心点
                        id2 = distances.get(k).getSource();// 某个武将
                        minid = k;
                    } else {
                        id = distances.get(minid).getDesc();
                        id2 = distances.get(minid).getSource();
                    }
                }
                // 遍历cluster聚类数组，找到类中心点id与最小距离目的武将id相同的聚类
                for (int n = 0; n < clusters.size(); n++) {
                    // 如果和中心点的id相同 则setError
                    if (clusters.get(n).getCenter() == id) {
                        clusters.get(n).addTextString(alltagetTextStringCollection.get(id2));// 将与该聚类中心距离最小的武将加入该聚类
                        break;
                    }
                }
            }
          }
        return clusters;
        }

    // 产生新的聚类中心点数组
    public Set<Integer> updateCenter() {
        Set<Integer> center = new HashSet<Integer>();
        for (int i = 0; i < K; i++) {
            center.add(i);
        }
        return center;
     }



    // 更新聚类中心, 求平均值
    public ArrayList<Cluster> updateCluster(ArrayList<Cluster> cluster) {
        ArrayList<Cluster> result = new ArrayList<Cluster>();
        // 重新产生的新的聚类中心组成的数组
        // k个聚类进行更新聚类中心
        for (int j = 0; j < K; j++) {
            ArrayList<String> ps = cluster.get(j).getOfCluster();// 该聚类的所有 文本字符串
            // 组成的数组
            ps.add(alltagetTextStringCollection.get(cluster.get(j).getCenter()));// 同时将该类中心对应的武将加入该武将数组
            int size = ps.size();// 该聚类的长度大小
            // 计算和，然后在计算平均值
            int sumrender = 0, sumtongshai = 0, sumwuli = 0, sumzhili = 0, sumjibin = 0, sumnubin = 0, sumqibin = 0, sumpolic = 0, sumqiangbin = 0, sumbinqi = 0, sumtongwu = 0, sumtongzhi = 0, sumtongwuzhi = 0, sumtongwuzhizheng = 0, sumsalary = 0;
            for (int k1 = 0; k1 < size; k1++) {
                sumsalary += ps.get(k1).getSalary();
            }
            // 产生新的聚类，然后加入到聚类数组中
            Cluster newCluster = new Cluster();
            newCluster.setCenter(j);
            // 计算平均值并构造新的武将对象
            newCluster.addTextString("");
            result.add(newCluster);
        }
        return result;

    }
    }

