/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.functons;

import java.io.Serializable;

@FunctionalInterface
public interface FunctionS<T,R> extends Serializable {


   R call(T t);

}
