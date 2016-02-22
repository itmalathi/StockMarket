///////////////////////////////////////////////////////////////////////////////////
////////////////SampleStockMarket//////////////////////////////////////////////////

package sampleStockMarket;

import java.util.*;

// Creates, manipulates and manages Stock Repository information

public class StockRepository {
	private static StockRepository instance;
	public List<Stock> stocks = new ArrayList<Stock>();

	private StockRepository() {

	}

	// Made as a singleton class
	public static StockRepository getInstance() {
		if (instance == null) {
			instance = new StockRepository();
		}
		return instance;
	}

	public void populateStocks() {
		// Get Sample Data
		stocks = getSampleData();
	}

	// Adds the sample data in the stock list
	private List<Stock> getSampleData() {
		stocks.add(new Stock("tea", ProductType.Common, 0, null, 100));
		stocks.add(new Stock("pop", ProductType.Common, 8, null, 100));
		stocks.add(new Stock("ale", ProductType.Common, 23, null, 60));
		stocks.add(new Stock("gin", ProductType.Preferred, 8, 2.0, 100));
		stocks.add(new Stock("joe", ProductType.Common, 13, null, 250));

		return stocks;
	}

	// Checks whether the given stock is valid or not
	boolean isValidStock(String stockName) {
		boolean isavailable = false;

		Stock stock = stocks.stream().filter(e -> e.getStockName().equalsIgnoreCase(stockName)).findFirst().get();
		if (stock != null)
			isavailable = true;
		return isavailable;
	}

	// Returns the required stock
	public Stock GetStock(String stockName) {
		Stock matchingStock = null;
		Stock stock = stocks.stream().filter(e -> e.getStockName().equalsIgnoreCase(stockName)).findFirst().get();
		if (stock != null) {
			return stock;
		}
		return matchingStock;
	}
}
