import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxGaugeModule } from 'ngx-gauge';
import { AppComponent } from './app.component';
import { SentimentAnalyzerComponent } from './sentiment-analyzer/sentiment-analyzer.component';
import { GaugeElementComponent } from './gauge-element/gauge-element.component';
import {Routes, RouterModule} from '@angular/router';


const appRoutes: Routes = [
  { path: 'sentiment' , component: SentimentWithIndicatorComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    SentimentAnalyzerComponent,
    GaugeElementComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgxGaugeModule,
    RouterModule.forRoot(appRoutes),
    AgGridModule.withComponents([TradedataComponent])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
