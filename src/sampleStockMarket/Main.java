///////////////////////////////////////////////////////////////////////////////////
////////////////SampleStockMarket//////////////////////////////////////////////////

package sampleStockMarket;

import java.util.Scanner;

public class Main {
	// To read the input from User
	static Scanner scan;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the trader name:");
		scan = new Scanner(System.in);
		String traderName = scan.nextLine();

		if (traderName != null && !traderName.isEmpty()) {
			traderName = "guest";
		}

		StockRepository.getInstance().populateStocks();

		boolean quit = false;
		String command;
		// Loop until user wants to exit the application
		while (!quit) {
			System.out.println();
			System.out.println("*************************************************************************");
			System.out.println("Press 1, to find the divident yield for the stock");
			System.out.println("Press 2 to find the P/E ratio for the stock");
			System.out.println("Press 3 to record the trades");
			System.out.println("Press 4 to Volume Weight Stock Price");
			System.out.println("Press 5 to get All Stock Index");
			System.out.println("Press 6 to quit the application");
			System.out.println("*************************************************************************");
			command = scan.nextLine();

			switch (command) {

			case "1": // Calculates dividend yield for a given stock
				dividendYieldForStock();
				break;
			case "2": // Calculates P/E ratio for a given stock
				PERatioForStock();
				break;
			case "3": // Records the trade information
				RecordTrade();
				break;
			case "4": // Calculates Volume weight stock price for a given stock
				VolumeWeightForStock();
				break;
			case "5": // Calculates All stock index
				AllStockIndex();
				break;
			case "6": // to quit the application
				quit = true;
				break;

			default:
				System.out.println("Invalid Option");
				break;
			}
		}
		System.out.println("Exiting the application");
	}

	// Calculates DividentYield for the given stock
	private static void dividendYieldForStock() {
		System.out.println("Enter the stock name (ex: TEA, POP, ALE, GIN, JOE)");
		String stockName = scan.nextLine();
		if (stockName != null && !stockName.isEmpty()) {
			// Gets the stock from stock repository
			Stock stock = StockRepository.getInstance().GetStock(stockName);
			if (stock != null) {
				System.out.println("Enter the price");
				String price = scan.nextLine();
				if (Utils.isDouble(price)) {
					double dprice = Double.parseDouble(price);
					if (dprice <= 0) {
						System.out.println("Price should be > 0");
						return;
					}

					System.out.format("Divident Yield value for the stock %s = %.3f\n", stockName,
							stock.GetDividentYield(dprice));
				} else {
					System.out.println("Invalid value");
				}

			} else {
				System.out.format("%s is not a valid stock. Please try again\n", stockName);
			}
		} else {
			System.out.println("Please enter the valid stock name");
		}
	}

	// Calculates P/E ration for the given stock
	private static void PERatioForStock() {
		System.out.println("Enter the stock name (ex: TEA, POP, ALE, GIN, JOE)");
		String stockName = scan.nextLine();
		;
		if (stockName != null && !stockName.isEmpty()) {
			// Get the stock from stock repository
			Stock stock = StockRepository.getInstance().GetStock(stockName);
			if (stock != null) {
				System.out.println("Enter the price");
				String price = scan.nextLine();

				if (Utils.isDouble(price)) {
					double dprice = Double.parseDouble(price);
					System.out.format("P/E ratio for the stock %s = %.3f\n", stockName, stock.PERatio(dprice));
				} else {
					System.out.println("Invalid Value");
				}

			} else {
				System.out.format("%s is not a valid stock. Please try again\n", stockName);
			}
		} else {
			System.out.println("Please enter the valid stock name");
		}
	}

	private static void VolumeWeightForStock() {
		System.out.println("Enter the stock name (ex: TEA, POP, ALE, GIN, JOE)");
		String stock = scan.nextLine();
		if (stock != null && !stock.isEmpty()) {
			// Calculates VolumeWeightStock for the given stock (past 5 minutes)
			double value = TradeRepository.getInstance().VolumeWeightStockPrice(stock, Utils.getStartTime());
			System.out.format("\nVolume weight for the stock %s = %.3f\n", stock, value);
		} else {
			System.out.println("Please enter the valid value for stock");
		}
	}

	private static void RecordTrade() {
		boolean quit = false;

		while (!quit) {
			System.out.println("Please enter the trade as per this structure (stockName, quantity, price, B/S)");

			String command = scan.nextLine();

			String[] args = command.split(",");

			// Validates the user input
			if (ValidateInputValues(command)) {
				TradeType buysell = args[3].equalsIgnoreCase("B") ? TradeType.Buy : TradeType.Sell;

				// Adds a trade in Trade repository
				TradeRepository.getInstance().AddTrade(new Trade(args[0].toLowerCase(), Integer.parseInt(args[1]),
						Double.parseDouble(args[2]), buysell, Utils.GetCurrentTime()));

				System.out.println("Trade is recorded successfully");
			}

			System.out.println("Do you want to enter another trade(y/n)? ");

			String option = scan.nextLine();

			if (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n")) {
				System.out.println("Invalid Value. Please enter y/n");
				quit = true;
			}
			if (option.equals("n")) {
				quit = true;
			}

		}
	}

	// Calculates all Stock Index
	private static void AllStockIndex() {
		double value = TradeRepository.getInstance().GetAllShareIndex();
		System.out.format("\nAll stock index = %.3f\n", value);
	}

	// Validates User Input
	private static boolean ValidateInputValues(String statement) {
		String[] args = statement.split(",");

		if (args.length != 4) {
			System.out.println("Invalid number of arguments");
			return false;
		}

		if (!StockRepository.getInstance().isValidStock(args[0])) {
			System.out.format("%s is not a valid stock\n", args[0]);
			return false;
		}

		if (Utils.isInteger(args[1])) {
			if (Integer.parseInt(args[1]) == 0) {
				System.out.println("Quantity should be > 0");
				return false;
			}
		} else {
			System.out.println("Invalid Quantity");
			return false;
		}

		if (Utils.isDouble(args[2])) {
			if (Double.parseDouble(args[2]) == 0) {
				System.out.println("Price should be > 0");
				return false;
			}
		} else {
			System.out.println("Invalid Price");
			return false;
		}

		if (!args[3].equalsIgnoreCase("B") && !args[3].equalsIgnoreCase("S")) {
			System.out.println("Invalid trade type");
			return false;
		}
		return true;

	}
}
