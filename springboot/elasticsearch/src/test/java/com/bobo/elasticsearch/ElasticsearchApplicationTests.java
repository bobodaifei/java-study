package com.bobo.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.bobo.elasticsearch.entity.Book;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ElasticsearchApplicationTests {

    //    @Autowired
    private RestHighLevelClient client;

    @Test
    void contextLoads() throws IOException {
        HttpHost host = HttpHost.create("http://127.0.0.1:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);


        client.close();
    }

    @Test
    void testContextLoads() throws IOException {
        HttpHost host = HttpHost.create("http://127.0.0.1:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);
        CreateIndexRequest request = new CreateIndexRequest("books");
        client.indices().create(request, RequestOptions.DEFAULT);

        client.close();
    }

    @Test
    void testContextLoads1() throws IOException {
        HttpHost host = HttpHost.create("http://127.0.0.1:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);
        CreateIndexRequest request = new CreateIndexRequest("books");
        //设置请求中的参数
        String json = "{\"mappings\":{\n" +
                "        \"properties\":{\n" +
                "            \"id\":{\n" +
                "                \"type\":\"keyword\"\n" +
                "            },\n" +
                "            \"name\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\",\n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"type\":{\n" +
                "                \"type\":\"keyword\"\n" +
                "            },\n" +
                "            \"des\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\",\n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"all\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        request.source(json, XContentType.JSON);

        client.indices().create(request, RequestOptions.DEFAULT);

        client.close();
    }


    @Test
    void testContextLoads2() throws IOException {
        HttpHost host = HttpHost.create("http://127.0.0.1:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);

        Book book = new Book();
        book.setId(1);
        book.setName("bobo");
        book.setDes("...");
        book.setType("yyy");

        IndexRequest request = new IndexRequest("books").id(book.getId().toString());
        String jsonString = JSON.toJSONString(book);
        request.source(jsonString, XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
        client.close();
    }

    @Test
    void testContextLoads3() throws IOException {
        HttpHost host = HttpHost.create("http://127.0.0.1:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);

        Book book = new Book();
        book.setId(1);
        book.setName("bobo");
        book.setDes("...");
        book.setType("yyy");
        List<Book> list = new ArrayList<>();
        list.add(book);

        BulkRequest bulkRequest = new BulkRequest();

        list.forEach(book0 -> {
            IndexRequest request = new IndexRequest("books").id(book0.getId().toString());
            String jsonString = JSON.toJSONString(book0);
            request.source(jsonString, XContentType.JSON);
            bulkRequest.add(request);
        });

        client.bulk(bulkRequest, RequestOptions.DEFAULT);
        client.close();
    }


    @Test
    void testContextLoads4() throws IOException {
        HttpHost host = HttpHost.create("http://127.0.0.1:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);
        GetRequest request = new GetRequest("books", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String s = response.getSourceAsString();
        System.out.println(s);
        client.close();
    }

    @Test
    void testContextLoads5() throws IOException {
        //*****************************************************
        HttpHost host = HttpHost.create("http://127.0.0.1:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);

        SearchRequest request = new SearchRequest("books");
        SearchSourceBuilder builder1 = new SearchSourceBuilder();
        builder1.query(QueryBuilders.termQuery("name", "bobo"));

        request.source(builder1);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        hits.forEach(item -> {
            String s = item.getSourceAsString();
            System.out.println(s);
        });
        client.close();
    }

}
