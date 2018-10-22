/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobData implements Serializable {
    private long campaignId;
    private String campaignName;
    private String trade1;
    private String trade2;
    private int version;
    private List<KeyMultiValuePair<String, String>> market;
    private List<KeyValuePair<String, String>> analysisObj;
    private List<KeyValuePair<String, String>> competingObj;
    private String beginDate;
    private String endDate;
    private List<Audience> audience;
    private boolean writeTagFile = false;
}
