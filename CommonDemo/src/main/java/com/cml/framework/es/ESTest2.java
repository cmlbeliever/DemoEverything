package com.cml.framework.es;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.params.Parameters;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
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
        JsonObject jsonObject = searchEvent(jestClient);

        System.out.println("查询到的结果");
        System.out.println(jsonObject);

        System.out.println("---------------------------getmapping-----------------");
        getMapping(jestClient);

//        System.out.println(indicesExists(jestClient));
    }

    private static void getMapping(JestClient jestClient) throws IOException {
        GetMapping getMapping = new GetMapping.Builder().addIndex("logstash-web-request-*").build();
        System.out.println(jestClient.execute(getMapping));
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
