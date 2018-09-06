/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package java8.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resource {
    private String cuid;

    private int id;

    private String label;

    @Override
    public String toString() {
        return "Resource{" +
                "cuid='" + cuid + '\'' +
                ", id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
