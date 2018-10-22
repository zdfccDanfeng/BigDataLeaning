/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.functons;

import java.io.Serializable;

@FunctionalInterface
public interface BiFunctionS<T,U,R> extends Serializable {

    R call(T t, U u);
}
