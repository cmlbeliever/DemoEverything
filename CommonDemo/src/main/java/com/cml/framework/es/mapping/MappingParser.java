package com.cml.framework.es.mapping;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MappingParser {

    public List<Index> parse(JsonObject mappingRoot) {
        Iterator<Map.Entry<String, JsonElement>> iterator = mappingRoot.entrySet().iterator();
        List<Index> indices = new ArrayList<>();

        while (iterator.hasNext()) {
            Map.Entry<String, JsonElement> key = iterator.next();

            List<Mapping> mappingList = new ArrayList<>();

            JsonObject mappingItem = key.getValue().getAsJsonObject().getAsJsonObject("mappings");

            Iterator<Map.Entry<String, JsonElement>> mappingItemIterator = mappingItem.entrySet().iterator();
            while (mappingItemIterator.hasNext()) {
                Map.Entry<String, JsonElement> next = mappingItemIterator.next();

                if ("_default_".equals(next.getKey())) {
                    continue;
                }

                Mapping mapping = new Mapping();
                mapping.setName(next.getKey());

                JsonObject properties = next.getValue().getAsJsonObject().getAsJsonObject("properties");

                List<MappingNode> nodes = getNodes("", properties);
                mapping.setNodes(nodes);

                mappingList.add(mapping);
            }
            Index index = new Index();
            index.setName(key.getKey());
            index.setMapping(mappingList);
            indices.add(index);
        }

        return indices;
    }

    private List<MappingNode> getNodes(String prefix, JsonObject properties) {
        List<MappingNode> mappingNodes = new ArrayList<>();

        Iterator<Map.Entry<String, JsonElement>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {

            Map.Entry<String, JsonElement> next = iterator.next();
            JsonObject value = next.getValue().getAsJsonObject();

            MappingNode mappingNode = new MappingNode();

            if (value.has("type")) {
                mappingNode.setName(StringUtils.isBlank(prefix) ? next.getKey() : prefix + "." + next.getKey());
                mappingNode.setType(value.get("type").getAsString());
                mappingNodes.add(mappingNode);
            } else if (value.has("properties")) {
                List<MappingNode> subNodes = getNodes(next.getKey(), value.getAsJsonObject("properties"));
                mappingNodes.addAll(subNodes);
            }

        }


        return mappingNodes;
    }
}
