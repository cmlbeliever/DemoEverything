package com.cml.framework.es;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;

import java.io.IOException;

/**
 * @Auther: cml
 * @Date: 2018-08-23 18:57
 * @Description:
 */
public class ESTest {
    public static void main(String[] args) {
        JestClientFactory jestClientFactory = new JestClientFactory();
//        String url = "http://192.168.15.200:9201";
        String url = "http://localhost:9200";
        jestClientFactory.setHttpClientConfig(new HttpClientConfig.Builder(url).multiThreaded(true).connTimeout(5000).build());
        JestClient jestClient = jestClientFactory.getObject();

        createIndices(jestClient);
        searchEvent(jestClient);

        System.out.println(indicesExists(jestClient));
    }

    public static JsonObject searchEvent(JestClient jestClient) {
//        JsonObject returnData = new JsonParser().parse(param).getAsJsonObject();
        Search search = new Search.Builder("").addType(Student.class.getSimpleName()).addIndex("school").build();
//      Gson gs = new Gson();
//      System.out.println("输入参数为：" + "\n" + gs.toJson(search));

        SearchResult result = null;
        try {
            result = jestClient.execute(search);
            if (result.isSucceeded()) {
                result.getHits(Student.class).forEach(t -> {
                    System.out.println(t.source);
                });
            }
            System.out.println("===>" + result.getJsonString() + "\n");
            System.out.println("本次查询共查到：" + "\n" + result.getTotal() + "个结果！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.getJsonObject();
    }

    public static JestResult indicesExists(JestClient jestClient) {
        IndicesExists indicesExists = new IndicesExists.Builder("taxware-output-invoice-service-prod*").build();
        JestResult result = null;
        try {
            result = jestClient.execute(indicesExists);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JestResult createIndices(JestClient jestClient) {
        Student student = new Student();
        //  new CreateIndex()
        Index index = new Index.Builder(student).index("school").type("myType").build();
        JestResult result = null;
        try {
            result = jestClient.execute(index);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
