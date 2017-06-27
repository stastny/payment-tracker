/**
 * Display data of memory for Payment Tracker
 */
package cz.komix.main;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import cz.komix.currency.CurrencyValue;

/**
 * The class for display data from memory to console The object is singleton.
 * 
 * @author stastny
 *
 */
public class DataPaymentDisplay {
	// define instance ReadCvsFile
	private static final DataPaymentDisplay INSTANCE = new DataPaymentDisplay();

	public static DataPaymentDisplay getInstance() {
		return INSTANCE;
	}

	/**
	 * Make display data to console
	 * 
	 * @param data
	 */
	public void displayData(List<String[]> data) {
		Logger log = Logger.getLogger("PaymentTracker - displayData");
		Hashtable<String, Double> hashData = new Hashtable<String, Double>();
		StringBuffer finalTable = new StringBuffer();
		if (data != null && data.size() > 0) {
			log.info("Set " + data.size() + " rows.");
			// suma of payments by currents
			for (String[] dataPayments : data) {
				if (Validate.getInstance().isValidateValue(dataPayments[1])) {
					hashData.put(dataPayments[0], ((hashData.get(dataPayments[0]) != null) ? hashData.get(dataPayments[0]) : new Double("0.0"))
									+ new Double(dataPayments[1]));
				}
			}
			// prepare table from display to console
			finalTable.append("Summary payments - 1.colum = currency, 2.column = suma of values, 3.column = Exchange Rates to USD");
			finalTable.append("\n");
			for (Enumeration<String> enums = hashData.keys(); enums.hasMoreElements();) {
				Object currency = enums.nextElement();
				Object value = hashData.get(currency);
				finalTable.append(currency);
				finalTable.append("\t");
				finalTable.append(value);
				finalTable.append("\t");
				finalTable.append(CurrencyValue.getInstance()
						.changeValueForUSD((String) currency, (Double) value));
				finalTable.append("\n");
				hashData.elements().nextElement();
			}

		}
		// display console
		System.out.print(finalTable);
		System.out
				.println("Set payment record - format currency SPACE value (if set quit PaimentTracker finished): ");
	}

}
