import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {TradeMarket} from '../model/trade-market.model';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {TradeMarketService} from '../services/trade-market.service';
import {SocketService} from '../services/socket.service';
import {ChartData} from '../model/chart-data.model';

@Component({
  selector: 'app-trade-dashboard',
  templateUrl: './trade-dashboard.component.html',
  styleUrls: ['./trade-dashboard.component.css']
})
export class TradeDashboardComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['userId', 'rate', 'currencyFrom', 'currencyTo', 'amountSell', 'amountBuy', 'timePlaced', 'originatingCountry'];
  dataSource: MatTableDataSource<TradeMarket>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  currencyFromLabels: string[] = [];
  currencyFromValues: number[] = [];
  currencyToLabels: string[] = [];
  currencyToValues: number[] = [];
  colorsCurrencyFrom: string[] = [];
  colorsCurrencyTo: string[] = [];


  constructor(private tradeMarketService: TradeMarketService, private webSocketService: SocketService) {
    this.refreshTradeMarketTableAndCharts();
    const tradeMarketPayload: TradeMarket[] = [];
    this.dataSource = new MatTableDataSource(tradeMarketPayload);
  }


  ngOnInit(): void {
    this.webSocketService.subscribe('/topic/trade-market', (): any => {
      this.refreshTradeMarketTableAndCharts();
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  refreshTradeMarketTableAndCharts(): void {
    this.tradeMarketService.findAll().subscribe(tradeMarkets => {
      this.dataSource = new MatTableDataSource<TradeMarket>(tradeMarkets);
    });

    this.tradeMarketService.tradeCountByCurrencyFrom().subscribe((chartData: ChartData[]) => {
      this.currencyFromLabels = chartData?.map(trade => trade?.label);
      this.currencyFromValues = chartData?.map(trade => trade?.count);
    });

    this.tradeMarketService.tradeCountByCurrencyTo().subscribe((chartData: ChartData[]) => {
      this.currencyToLabels = chartData?.map(trade => trade?.label);
      this.currencyToValues = chartData?.map(trade => trade?.count);
    });
  }

}
