import { Component, OnInit } from '@angular/core';
import { SentimentService } from '../sentiment-analyzer/sentiment.service';

@Component({
  selector: 'app-gauge-element',
  templateUrl: './gauge-element.component.html',
  styleUrls: ['./gauge-element.component.css']
})
export class GaugeElementComponent implements OnInit {

  constructor(private sentimentService: SentimentService) { }

  gaugeType = "semi";
  gaugeValue = 0;
  gaugeLabel = "Sentometer";
  gaugeAppendText = "/";
  min=0;
  max=4;
  size=200;

  //After point 'x' which color is applied.
  thresholdConfig = {
    '1': {color: 'red'},
    '2': {color: 'orange'},
    '3': {color: 'green'}
};


  ngOnInit() {
   this.sentimentService.$isFoundResult
      .subscribe( (data) => {
        this.gaugeValue = data.sentimentScore;
        this.gaugeAppendText = "(" + data.sentimentResultText +")";
      }) 
  }

}
