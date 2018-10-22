/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.MockData;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static <T> List<T> prepareData(T ... t){

        return Arrays.asList(t);
    }
}
