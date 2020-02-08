package com.amitru.psanalyzer.premptivesentimentanalyzer.main;


public class SentimentBean {
	private String sentiText;
	private String sentiment;
	private Integer sentimentScore;
	
	public SentimentBean() {}

	public SentimentBean(String sentiText) {
		this.sentiText = sentiText;
	}

	public String getSentiText() {
		return sentiText;
	}

	public void setSentiText(String sentiText) {
		this.sentiText = sentiText;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public Integer getSentimentScore() {
		return sentimentScore;
	}

	public void setSentimentScore(Integer sentimentScore) {
		this.sentimentScore = sentimentScore;
	}

	
	
	
	
	
	
}
