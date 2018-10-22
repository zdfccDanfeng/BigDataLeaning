/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package SparkLeanrning.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyValuePair<K extends Serializable,V extends Serializable> implements Serializable {

    private K key;

    private V value;


}
