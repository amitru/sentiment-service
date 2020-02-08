package com.amitru.psanalyzer.premptivesentimentanalyzer.main;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface SentimentRepository extends MongoRepository<SentimentResult, Integer>{

}
