/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Job;

import org.junit.Test;

import BaseTest.BaseTest;

public class GroupTestTest extends BaseTest {

    @Test
    public void testGroup() {

        GroupTest groupTest=new GroupTest();

        groupTest.run(getSparkSession(),null,null);
    }
}