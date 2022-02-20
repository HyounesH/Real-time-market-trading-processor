export class TradeMarket {
  id?: string;
  userId: string;
  currencyFrom: string;
  currencyTo: string;
  amountSell: number;
  amountBuy: number;
  rate: number;
  timePlaced: Date;
  originatingCountry: string;

}
