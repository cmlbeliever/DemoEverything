package com.cml.framework.es;

import com.cml.framework.es.mapping.Index;
import com.cml.framework.es.mapping.MappingParser;
import com.google.gson.*;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.params.Parameters;
import org.elasticsearch.action.admin.indices.get.GetIndexAction;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsIndexRequest;
import org.elasticsearch.action.admin.indices.template.get.GetIndexTemplatesRequestBuilder;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.action.admin.indices.RestGetIndicesAction;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cml
 * @Date: 2018-08-23 18:57
 * @Description:
 */
public class ESTest2 {
    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();
//        String url = "http://192.168.15.200:9201";
        String url = "http://localhost:9200";
        jestClientFactory.setHttpClientConfig(new HttpClientConfig.Builder(url).multiThreaded(true).connTimeout(5000).build());
        JestClient jestClient = jestClientFactory.getObject();

//        System.out.println(indicesExists(jestClient));

//        createIndices(jestClient);
//        JsonObject jsonObject = searchEvent(jestClient);
//
//        System.out.println("查询到的结果");
//        System.out.println(jsonObject);

//        System.out.println("-----------------------------getIndex-------------------------------");
//        getIndex(jestClient);
//        System.out.println("-----------------------------getIndex-------------------------------");

        System.out.println("---------------------------getmapping-----------------");
        getMapping(jestClient);

//        System.out.println(indicesExists(jestClient));
    }

    /**
     * 获取所有index
     *
     * @param jestClient
     * @throws IOException
     */
    private static void getIndex(JestClient jestClient) throws IOException {
        Cat build = new Cat.IndicesBuilder().build();
        CatResult execute = jestClient.execute(build);
        JsonArray result = execute.getJsonObject().getAsJsonArray("result");
        for (int i = 0; i < result.size(); i++) {
            JsonObject indexObj = result.get(i).getAsJsonObject();
            String indexName = indexObj.get("index").getAsString();
            System.out.println(indexName);
        }
//        System.out.println(result);
    }

    private static void getMapping(JestClient jestClient) throws IOException {
        GetMapping getMapping = new GetMapping.Builder().addIndex("logstash-*").build();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JestResult execute = jestClient.execute(getMapping);

        JsonObject mappingRoot = execute.getJsonObject();

        List<Index> indices = new MappingParser().parse(mappingRoot);
        System.out.println(new Gson().toJson(indices));
    }

    public static JsonObject searchEvent(JestClient jestClient) {
//        JsonObject returnData = new JsonParser().parse(param).getAsJsonObject();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery()).size(1);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        Search search = new Search.Builder(searchSourceBuilder.toString()).addType("doc")
//                .setParameter(Parameters.SCROLL, "10d")
                .addIndex("logstash-web-request-*").build();

        SearchResult result = null;
        System.out.println("===>" + search.toString());
        try {
            result = jestClient.execute(search);
            if (result.isSucceeded()) {
                result.getHits(Map.class).forEach(t -> {
                    System.out.println(t.source);
                });
            }
            System.out.println("===>" + result.getJsonString() + "\n");

            System.out.println("本次查询共查到：" + "\n" + result.getTotal() + "个结果！");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===>total:" + result.getTotal());
        System.out.println("data:" + result.getHits(JsonObject.class));
        return result.getJsonObject();
    }

    public static JestResult indicesExists(JestClient jestClient) {
        IndicesExists indicesExists = new IndicesExists.Builder("logstash-web-request-*").build();
        JestResult result = null;
        try {
            result = jestClient.execute(indicesExists);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
