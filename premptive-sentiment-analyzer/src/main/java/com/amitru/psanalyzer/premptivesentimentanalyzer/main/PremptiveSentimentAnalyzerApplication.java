package com.amitru.psanalyzer.premptivesentimentanalyzer.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient
public class PremptiveSentimentAnalyzerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PremptiveSentimentAnalyzerApplication.class, args);
	}
	
	
	 @Bean public Sampler defaultSampler() { 
		 return Sampler.ALWAYS_SAMPLE; 
	}


}
