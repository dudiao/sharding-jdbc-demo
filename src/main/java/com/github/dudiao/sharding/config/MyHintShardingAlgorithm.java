package com.github.dudiao.sharding.config;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author songyinyin
 * @since 2021/06/09 0:55
 */
public class MyHintShardingAlgorithm implements HintShardingAlgorithm<Integer> {
    /**
     * Sharding.
     *
     * <p>sharding value injected by hint, not in SQL.</p>
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding result for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(final Collection<String> availableTargetNames, final HintShardingValue<Integer> shardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : availableTargetNames) {
            for (Integer value : shardingValue.getValues()) {
                if (each.endsWith(String.valueOf(value))) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
