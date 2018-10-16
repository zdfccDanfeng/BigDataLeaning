/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package ML.KMeans;

import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cluster {

    private int center; //聚类中心id

    private ArrayList<String> ofCluster=new ArrayList<>();

    public void addTextString(String string){
        if(!ofCluster.contains(string)){
            this.ofCluster.add(string);
        }
    }
}
