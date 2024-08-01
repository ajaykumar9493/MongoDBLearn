package com.learn.mongodb.controller;

import com.learn.mongodb.repository.JobRepository;
import com.learn.mongodb.modules.JobDetails;
import com.learn.mongodb.repository.SearchRepository;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobDetailsController {

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/getAllJobPosts")
    public List<JobDetails> getJobDetails(){
        System.out.println(">>> getting all jobdetails ::: ");
        return jobRepository.findAll();
    }

    @PostMapping("/postJobPosts")
    public JobDetails postJobDetails(@RequestBody JobDetails jobDetails){
        System.out.println(">> posting job in Database ::: ");
        return jobRepository.save(jobDetails);
    }



    @GetMapping("/search/{text}")
    public List<JobDetails> getSearchJobDetails(@PathVariable String text){
        System.out.println(">>> getting all jobdetails ::: "+text);

        return searchRepository.searchText(text);
    }


}
