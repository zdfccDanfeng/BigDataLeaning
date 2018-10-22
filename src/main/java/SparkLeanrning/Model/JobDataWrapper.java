/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public class JobDataWrapper implements Serializable {

    private JobData data;

    public JobDataWrapper(JobData data) {
        this.data = data;
    }

    public JobData getData() {
        return this.data;
    }

    public long getCampaignId() {
        return this.data.getCampaignId();
    }

    public String getCampaignName() {
        return this.data.getCampaignName();
    }

    public int getVersion() {
        return this.data.getVersion();
    }

    public String getBeginDate() {
        return this.data.getBeginDate();
    }

    public String getEndDate() {
        return this.data.getEndDate();
    }

    public Optional<String> getTrade1() {
        return Optional.ofNullable(this.data.getTrade1());
    }

    public Optional<String> getTrade2() {
        return Optional.ofNullable(this.data.getTrade2());
    }

    public Optional<List<KeyMultiValuePair<String, String>>> getMarket() {
        return toOptionalList(this.data.getMarket());
    }

    public Optional<List<KeyValuePair<String, String>>> getAnalysisObj() {
        return toOptionalList(this.data.getAnalysisObj());
    }

    public Optional<List<KeyValuePair<String, String>>> getCompetingObj() {
        return toOptionalList(this.data.getCompetingObj());
    }

    public Optional<List<Audience>> getAudience() {
        return toOptionalList(this.data.getAudience());
    }

    public boolean isWriteTagFile() {
        return this.data.isWriteTagFile();
    }

    public static <T> Optional<List<T>> toOptionalList(List<T> originList) {
        return Optional.ofNullable(originList).filter((list) -> {
            return list.size() > 0;
        });
    }

    public String toString() {
        return "JobDataWrapper(data=" + this.getData() + ")";
    }

}
