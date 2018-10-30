/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.MrComputer;

import java.util.Objects;

/**
 *
 * 回写任务数据，包含Url 和 数据结构
 * @param <T>
 */
public class ResponseData<T> {

    private final String url;

    private final T content;

    public ResponseData(String url,T data){
        this.content=data;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public T getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "url='" + url + '\'' +
                ", content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseData<?> that = (ResponseData<?>) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url, content);
    }
}
