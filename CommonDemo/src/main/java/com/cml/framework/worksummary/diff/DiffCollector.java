package com.cml.framework.worksummary.diff;

import com.cml.framework.worksummary.diff.comparator.DiffComparator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiffCollector {
    /**
     * 对比两组元素
     *
     * @param srcList 需要计算的数据
     * @param stdList 对比的标准数据
     * @param <T>
     * @return srcList同stdList不同数据的集合
     */
    public static <T> DiffContainer<T> calDiff(List<T> srcList, List<T> stdList, DiffComparator<T> diffComparator) {

        if (null == srcList || null == stdList) {
            throw new IllegalArgumentException("srcList or stdList can not be null!!!");
        }

        DiffContainer<T> diffContainer = new DiffContainer<>();
        if (srcList.isEmpty()) {
            diffContainer.setElementLostList(stdList);
            return diffContainer;
        }
        if (stdList.isEmpty()) {
            diffContainer.setElementAddList(srcList);
            return diffContainer;
        }

        //根据T生成对应的key转换成map
        Map<String, T> srcMap = srcList.stream().collect(Collectors.toMap(diffComparator::generateKey, value -> value));
        Map<String, T> stdMap = stdList.stream().collect(Collectors.toMap(diffComparator::generateKey, value -> value));

        srcMap.forEach((k, v) -> {
            if (stdMap.containsKey(k)) {
                //两边都有数据，判断是否相同
                if (diffComparator.equals(v, stdMap.get(k))) {
                    diffContainer.elementUnChange(v);
                } else {
                    diffContainer.elementChanged(v);
                }
                stdMap.remove(k);
            } else {
                diffContainer.elementAdd(v);
            }
        });

        //计算丢失的数据
        stdMap.forEach((k, v) -> {
            if (!srcMap.containsKey(k)) {
                diffContainer.elementLost(v);
            }
        });


        return diffContainer;
    }
}
