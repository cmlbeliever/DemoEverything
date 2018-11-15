package com.cml.framework.es.mapping;

import java.util.ArrayList;
import java.util.List;

public class Mapping {
    private String name;
    private List<MappingNode> nodes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MappingNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<MappingNode> nodes) {
        this.nodes = nodes;
    }
}
