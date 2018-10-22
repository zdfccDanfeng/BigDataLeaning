/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Wise_KG {

    private String cuid;
    private int event_day;
    private List<Map<String, String>> extProperties;
    private Map<String, List<String>> properties;
    private String trade1;
    private String trade2;

    private List<String> intentions;
}
