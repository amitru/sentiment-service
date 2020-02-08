package com.amitru.psanalyzer.premptivesentimentanalyzer.main;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



import edu.stanford.nlp.pipeline.StanfordCoreNLP;



@RestController
public class SentimentController {
	
	@Autowired
	private Environment env;
	
	/*
	 * @Autowired private SentimentRepository repository;
	 */
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StanfordCoreNLP stanfordCoreNLP;
	
	@Autowired
	SentimentProviderService sp;
	
	


	@CrossOrigin(origins = "*")
	@PostMapping("/sentiment/compute")
	public SentimentResult computeSentiment(@RequestBody SentimentBean sBean) {
		
		int port = (Integer.parseInt(env.getProperty("local.server.port")));
		
		logger.info("##########Request is coming in {}",port);
		
		SentimentResult sentiResult = sp.getSentiment(sBean, stanfordCoreNLP);
		
		//repository.save(sentiResult);
		
		//logger.info("##########Added Sentiment in DB with ID- {}", sentiResult.getId());
		
		return sentiResult;
	}
	
	/*
	 * @PostMapping("/sentiments/findAll") public List<SentimentResult>
	 * getSentiments() { return repository.findAll(); }
	 */
	
}
