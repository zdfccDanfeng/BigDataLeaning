/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning;

import static org.junit.Assert.*;

import org.junit.Test;

import BaseTest.BaseTest;

public class ClusterTextTest extends BaseTest {

    @Test
    public void run() {
        ClusterText clusterText=new ClusterText();
        clusterText.run(sparkSession,null,null);
    }
}