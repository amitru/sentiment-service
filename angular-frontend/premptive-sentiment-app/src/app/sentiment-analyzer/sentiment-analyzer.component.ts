import { Component, OnInit } from '@angular/core';
import { SentimentService } from './sentiment.service';

@Component({
  selector: 'app-sentiment-analyzer',
  templateUrl: './sentiment-analyzer.component.html',
  styleUrls: ['./sentiment-analyzer.component.css']
})
export class SentimentAnalyzerComponent implements OnInit {

   constructor( private service:SentimentService) {}

    ngOnInit() {}

    sentiText="";

    postData ;

    doCheckTypeAhead(event) {
      if(this.sentiText.length>10 && event.data ==".") {
        this.postData = {"sentiText" : this.sentiText};
        this.service.checkSentiment(this.postData);
      }
    }

    onClear() {
      this.sentiText ="";
    }

    onCreatePost(postData: {sentiText: string }) {
        this.service.checkSentiment(postData);
    }
}

export interface SentimentResultEventArgs {
   sentimentScore: string;
   sentimentResultText: string;
}
