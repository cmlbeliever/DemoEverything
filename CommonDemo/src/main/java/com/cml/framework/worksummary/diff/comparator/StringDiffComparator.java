package com.cml.framework.worksummary.diff.comparator;

public class StringDiffComparator implements DiffComparator<String> {
    @Override
    public String generateKey(String s) {
        return s == null ? "" : s;
    }

    @Override
    public boolean equals(String src, String std) {
        if (null == src || null == std) {
            return false;
        }
        return src.equals(std);
    }
}
