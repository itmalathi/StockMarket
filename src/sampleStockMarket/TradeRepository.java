///////////////////////////////////////////////////////////////////////////////////
////////////////SampleStockMarket//////////////////////////////////////////////////

package sampleStockMarket;

import java.util.*;
import java.util.stream.Collectors;

//Creates, manipulates and manages trade repository information 
public class TradeRepository {
	private static TradeRepository instance;
	List<Trade> trades = new ArrayList<Trade>();

	private TradeRepository() {

	}

	// Made as a singleton class
	public static TradeRepository getInstance() {
		if (instance == null) {
			instance = new TradeRepository();
		}
		return instance;
	}

	// Adds a trade in the repository
	public void AddTrade(Trade newTrade) {
		trades.add(newTrade);
	}

	// Calculates VolumeWeightStockPrice for the given stock
	public double VolumeWeightStockPrice(String stock, Date startTime) {
		int totalCount = 0;
		double totalTransaction = 0;

		// filter only the required stocks and find the volume weight stock
		// price
		List<Trade> stockTrades = trades.stream()
				.filter(e -> e.getStockName().equalsIgnoreCase(stock) && startTime.compareTo(e.getTimeStamp()) < 0)
				.collect(Collectors.toList());

		if (stockTrades.size() == 0)
			return 0;

		System.out.println("\t\t\tLIST OF SELECTED TRADES");
		for (Trade item : stockTrades) {
			totalTransaction += item.getPrice() * item.getQuantity();
			totalCount += item.getQuantity();
			System.out.println(item);
		}

		return totalTransaction / totalCount;
	}

	public List<String> GetTradedStocks() {
		return trades.stream().map(t -> t.getStockName()).distinct().collect(Collectors.toList());
	}

	public double GetAllShareIndex() {
		List<String> tradedStocks = GetTradedStocks();

		if (tradedStocks == null) {
			return 0;
		}

		if (tradedStocks.size() == 0) {
			return 0;
		}

		double transactionSum = 0;

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1970);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);

		Date dayStartTime = cal.getTime();

		for (String stock : tradedStocks) {
			if (transactionSum == 0) {
				transactionSum = VolumeWeightStockPrice(stock, dayStartTime);
			} else {
				transactionSum *= VolumeWeightStockPrice(stock, dayStartTime);
			}
		}

		return Math.pow(transactionSum, tradedStocks.size());
	}
}
