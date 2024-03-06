package com.example.uolcoursereview.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.uolcoursereview.dto.CourseReviewES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ElasticSearchQuery {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "course_reviews";

    public String createOrUpdateDocument(CourseReviewES courseReviewES) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(courseReviewES.getId())
                .document(courseReviewES)
        );

        if(response.result().name().equals("Created")){
            return new StringBuilder("Document has been successfully created.").toString();
        }else if(response.result().name().equals("Updated")){
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }


    public List<CourseReviewES> searchAllDocuments() throws IOException {
        System.out.println("searchAllDocuments start...");

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, CourseReviewES.class);
        List<Hit> hits = searchResponse.hits().hits();
        System.out.println("Total: " + hits.size());
        List<CourseReviewES> courseReviewESList = new ArrayList<>();
        for(Hit object : hits){
            System.out.print(((CourseReviewES) object.source()));
            courseReviewESList.add((CourseReviewES) object.source());
        }

        System.out.println("searchAllDocuments...");
        return courseReviewESList;
    }

    public List<CourseReviewES> search(String searchText) throws IOException {
//        String searchText = "John";
        SearchResponse<CourseReviewES> searchResponse = elasticsearchClient.search(s -> s
                .index("course_reviews")
                .query(q -> q
                        .match(t -> t
                                .field("review")
                                .query(searchText))), CourseReviewES.class);

        List<Hit<CourseReviewES>> hits = searchResponse.hits().hits();

        List<CourseReviewES> courseReviewESList = new ArrayList<>();

        for(Hit object : hits){
            System.out.print(((CourseReviewES) object.source()));
            courseReviewESList.add((CourseReviewES) object.source());
        }
        return courseReviewESList;
    }

}
