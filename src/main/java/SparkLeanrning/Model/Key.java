/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Key implements Serializable {

    private String brand;

    private int pdate;

    private String cuid;
}
