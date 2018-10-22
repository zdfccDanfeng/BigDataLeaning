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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyMultiValuePair<K extends Serializable, V extends Serializable> implements Serializable {

    private K key;

    private List<V> value;
}
