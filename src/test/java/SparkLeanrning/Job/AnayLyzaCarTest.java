/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Job;

import static org.junit.Assert.*;

import org.junit.Test;

import BaseTest.BaseTest;

public class AnayLyzaCarTest extends BaseTest {

    @Test
    public void run() {

        AnayLyzaCar anayLyzaCar=new AnayLyzaCar();
        anayLyzaCar.run(sparkSession,null,null);
    }
}