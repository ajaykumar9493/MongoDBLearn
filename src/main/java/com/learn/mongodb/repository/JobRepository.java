package com.learn.mongodb.repository;

import com.learn.mongodb.modules.JobDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<JobDetails,String> {
}
