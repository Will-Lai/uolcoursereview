package com.example.uolcoursereview.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.elasticsearch.client.*;


@Configuration
public class ElasticSearchConfig {

    @Value("${es.host}")
    private String host;

    @Value("${es.port}")
    private Integer port;


    @Bean
    public org.elasticsearch.client.RestClient getRestClient() {
        org.elasticsearch.client.RestClient restClient = RestClient.builder(
                new HttpHost(host, port)).build();
        return restClient;
    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport() {
        return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient getElasticsearchClient() {
        ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
        return client;
    }


}
