package com.cml.framework.worksummary.diff;

import java.util.ArrayList;
import java.util.List;

public class DiffContainer<T> {
    /**
     * 新增的元素
     */
    private List<T> elementAddList = new ArrayList<>();
    /**
     * 减少的元素
     */
    private List<T> elementLostList = new ArrayList<>();
    /**
     * 改变的元素
     */
    private List<T> elementChangeList = new ArrayList<>();
    /**
     * 未变的元素
     */
    private List<T> elementUnChangeList = new ArrayList<>();

    public void elementAdd(T t) {
        elementAddList.add(t);
    }

    public void elementLost(T t) {
        elementLostList.add(t);
    }

    public void elementChanged(T t) {
        elementChangeList.add(t);
    }

    public void elementUnChange(T t) {
        elementUnChangeList.add(t);
    }

    public List<T> getElementUnChangeList() {
        return elementUnChangeList;
    }

    public void setElementUnChangeList(List<T> elementUnChangeList) {
        this.elementUnChangeList = elementUnChangeList;
    }

    public List<T> getElementAddList() {
        return elementAddList;
    }

    public void setElementAddList(List<T> elementAddList) {
        this.elementAddList = elementAddList;
    }

    public List<T> getElementLostList() {
        return elementLostList;
    }

    public void setElementLostList(List<T> elementLostList) {
        this.elementLostList = elementLostList;
    }

    public List<T> getElementChangeList() {
        return elementChangeList;
    }

    public void setElementChangeList(List<T> elementChangeList) {
        this.elementChangeList = elementChangeList;
    }

    @Override
    public String toString() {
        return "DiffContainer{" +
                "elementAddList=" + elementAddList +
                ", elementLostList=" + elementLostList +
                ", elementChangeList=" + elementChangeList +
                ", elementUnChangeList=" + elementUnChangeList +
                '}';
    }
}
