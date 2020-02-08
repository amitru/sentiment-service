package com.amitru.psanalyzer.premptivesentimentanalyzer.main;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

@Service
public class SentimentProviderService {

	/**
	 * This method will be called at server startup and load the core
	 * class StandfordCoreNLP , using which the sentiment is extracted.
	 * @return
	 */
	@Bean
	public StanfordCoreNLP stanfordCoreNLP() {
		Properties props = new Properties();
		props.put("ner.applyFineGrained", "0");
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref,sentiment");
		return new StanfordCoreNLP(props);
	}

	/**
	 * Method getSentiment
	 * @param SentimentBean sb
	 * @param StanfordCoreNLP pipeline
	 * @return SentimentResult
	 */
	public SentimentResult getSentiment(SentimentBean sb, StanfordCoreNLP pipeline) {

		SentimentResult finalResult = new SentimentResult();
		Integer sentenceCount = 0;
		
		List<SentimentBean> result = new ArrayList<SentimentBean>();
		SentimentBean sentimentBean = null;

		Annotation document = new Annotation(sb.getSentiText());
		pipeline.annotate(document);

		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

		for (CoreMap sentence : sentences) {
			sentenceCount++;
			sentimentBean = new SentimentBean();
			Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
			sentimentBean.setSentiText(sentence.toString());
			sentimentBean.setSentimentScore(RNNCoreAnnotations.getPredictedClass(tree));
			sentimentBean.setSentiment(sentence.get(SentimentCoreAnnotations.SentimentClass.class));
			result.add(sentimentBean);
		}
		
		finalResult.setsBean(result);
		finalResult.setSentences(sentenceCount);
		
		OptionalDouble average = result
	            .stream()
	            .mapToDouble(sBean -> sBean.getSentimentScore())
	            .average();
		
		Double finalAvg = Math.round(average.getAsDouble() * 10) / 10.0;
		finalResult.setFinalSentimentScore(finalAvg);
		finalResult.setFinalSentiment(getSentimentValue(finalAvg));
		return finalResult;

	}
	
	/*
	 * 0: "Very Negative" 
	 * 1: "Negative" 
	 * 2: "Neutral" 
	 * 3: "Positive" 
	 * 4: "Very Positive"
	 */
			
	private static String getSentimentValue(Double score) {
		String scoreValue="";
		if(score==0) return "Very Negative";
		
		if (isBetween(score, 0.1d, 1.9d)) {
			return "Negative";
			} else if (isBetween(score, 2d, 2.5d)) {
				return "Neutral";
			} else if (isBetween(score, 2.6d, 2.9d)) {
				 return "Positive";
			}else if (isBetween(score, 3d, 4d)) {
				return "Very Positive";
			}
		return scoreValue;
	}
	
	public static boolean isBetween(Double x, Double lower, Double upper) {
		  return lower <= x && x <= upper;
		}

}
