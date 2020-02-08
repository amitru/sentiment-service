package com.amitru.psanalyzer.premptivesentimentanalyzer.main;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SentimentResult {
	
	@Id
	private BigInteger id;
	
	Integer sentences;
	Double finalSentimentScore;
	String finalSentiment;
	List<SentimentBean> sBean;
	
	public Integer getSentences() {
		return sentences;
	}
	public void setSentences(Integer sentences) {
		this.sentences = sentences;
	}
	public Double getFinalSentimentScore() {
		return finalSentimentScore;
	}
	public void setFinalSentimentScore(Double finalSentimentScore) {
		this.finalSentimentScore = finalSentimentScore;
	}
	public String getFinalSentiment() {
		return finalSentiment;
	}
	public void setFinalSentiment(String finalSentiment) {
		this.finalSentiment = finalSentiment;
	}
	public List<SentimentBean> getsBean() {
		return sBean;
	}
	public void setsBean(List<SentimentBean> sBean) {
		this.sBean = sBean;
	}
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

}
