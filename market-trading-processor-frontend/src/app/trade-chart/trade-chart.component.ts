import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-trade-chart',
  templateUrl: './trade-chart.component.html',
  styleUrls: ['./trade-chart.component.css']
})
export class TradeChartComponent implements OnInit {
  @Input() labels: string[] = ['Loading'];
  @Input() values: number[] = [100];
  @Input() title: string;

  chartOptions = {
    responsive: true
  };

  constructor() {
  }

  ngOnInit(): void {
    this.chartOptions['title'] = {
      display: true,
      text: this.title
    };
  }

}
