package com.learn.mongodb.repository;

import com.learn.mongodb.modules.JobDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository {

    public List<JobDetails> searchText(String text);

}
