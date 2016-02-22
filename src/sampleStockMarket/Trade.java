///////////////////////////////////////////////////////////////////////////////////
////////////////SampleStockMarket//////////////////////////////////////////////////

package sampleStockMarket;

import java.util.Date;

enum TradeType {
	Buy, Sell
}

// Contains Trade information
public class Trade {
	private String stockName;
	private int quantity;
	private double price;
	TradeType tradeType;
	Date timeStamp;

	public Trade(String stockName, int quantity, double price, TradeType tradeType, Date timeStamp) {
		this.stockName = stockName;
		this.quantity = quantity;
		this.price = price;
		this.tradeType = tradeType;
		this.timeStamp = timeStamp;
	}

	// Overrides to display in a proper way
	@Override
	public String toString() {
		System.out.format("StockName : %s\tQuantity : %d\tPrice : %.3f\tTradeType : %s\t\tTimeStamp : %s", stockName,
				quantity, price, tradeType, timeStamp);
		return "";
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
