package com.learn.mongodb.service;

import com.learn.mongodb.modules.JobDetails;
import com.learn.mongodb.repository.SearchRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.AggregateIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchService implements SearchRepository {

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter mongoConverter;

    @Override
    public List<JobDetails> searchText(String text) {

        List<JobDetails> joblist = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("mongodb");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "default")
                                .append("text",
                                        new Document("query", text)
                                                .append("path", Arrays.asList("desc", "techs")))),   //here we keep the key's where the search word should be checked
                new Document("$sort",
                        new Document("exp", 1L)),     // 1 - indicates in the order of ascending order
                new Document("$limit", 5L)));         // 5 - Max 5 results only

        for(Document doc: result){
            joblist.add(mongoConverter.read(JobDetails.class,doc));    // here MongoConverters are used to convert doc -> object
        }

        System.out.println(">> After Applying the Filters - java, in [desc, techs] ::: ");
        return joblist;
    }
}
