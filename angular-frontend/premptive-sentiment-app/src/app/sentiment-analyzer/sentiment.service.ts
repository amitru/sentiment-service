import { Injectable } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { SentimentResultEventArgs } from './sentiment-analyzer.component';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SentimentService {

  sentimentServiceURL = "http://localhost:8009/sentiment/compute";
  //sentimentServiceURL ="http://localhost:8765/premptive-sentiment-analyzer/sentiment/compute";

  $isFoundResult = new EventEmitter();

  sentimentRes: SentimentResultEventArgs = {
    sentimentScore: "",
    sentimentResultText: ""
  }


  constructor(private http: HttpClient) { }

  checkSentiment(postData) {
    this.http.post(this.sentimentServiceURL, postData
    )
      .subscribe(responseData => {
        console.log(responseData);
        for (const key in responseData) {
          if (key == 'finalSentimentScore')
            this.sentimentRes.sentimentScore = (responseData[key]);
          else if (key == 'finalSentiment')
            this.sentimentRes.sentimentResultText = (responseData[key]);
        }
        this.$isFoundResult.emit(this.sentimentRes);
      });
  }
}
