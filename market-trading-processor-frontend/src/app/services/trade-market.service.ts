import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import {Observable} from 'rxjs';
import {TradeMarket} from '../model/trade-market.model';
import {ChartData} from '../model/chart-data.model';

const API_BASE_URL = environment.API_BASE_URL;

@Injectable({
  providedIn: 'root'
})
export class TradeMarketService {

  constructor(private http: HttpClient) {

  }


  findAll(): Observable<TradeMarket[]> {
    return this.http.get<TradeMarket[]>(`${API_BASE_URL}/all`);
  }

  tradeCountByCurrencyFrom(): Observable<ChartData[]> {
    return this.http.get<ChartData[]>(`${API_BASE_URL}/trade-by-currency-from`);
  }

  tradeCountByCurrencyTo(): Observable<ChartData[]> {
    return this.http.get<ChartData[]>(`${API_BASE_URL}/trade-by-currency-to`);
  }
}
