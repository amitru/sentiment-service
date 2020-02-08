package com.amitru.psanalyzer.premptivesentimentanalyzer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

/**
 * Created by vedenin on 30.01.16.
 */
public class StanfordCoreNLPTest {

    public static String findSentiment(String text) {
    	
    	String result="";
        Properties props = new Properties();
        props.put("ner.applyFineGrained", "0");
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref,sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);
        // run all Annotators on this text
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            result = result + sentence + " -  " + sentiment;
        }
        return result;
    }
    
    public static void main(String[] args) { 
    	String text="\"this is a test. trying second. this a a tried and not so good.\"";
    	String res = StanfordCoreNLPTest.findSentiment(text);
    	System.out.println(res);
	}
}
