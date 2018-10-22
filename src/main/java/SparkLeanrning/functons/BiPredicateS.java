/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.functons;

import java.io.Serializable;

@FunctionalInterface
public interface BiPredicateS<T,U> extends Serializable {

    boolean test(T t, U u);

}
