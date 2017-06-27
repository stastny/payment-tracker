package cz.komix.currency;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.regex.Pattern;

/**
 * Modul for Exchange Rates
 * 
 * @author stastny
 *
 */
public class CurrencyValue {

	// define instance ReadCvsFile
	private static final CurrencyValue INSTANCE = new CurrencyValue();

	public static CurrencyValue getInstance() {
		return INSTANCE;
	}

	public Hashtable<String, Double> hashtable = null;
	private NumberFormat formatter = new DecimalFormat("#0.00");


	public CurrencyValue() {
		super();
		initValue();
	}

	/**
	 * Initialize Exchange Rates in memory
	 */
	private void initValue() {
		this.hashtable = new Hashtable<String, Double>();
		this.hashtable.put("HKD", new Double("45.6"));
		this.hashtable.put("RMB", new Double("12.4"));
		this.hashtable.put("KLM", new Double("1.8"));
		this.hashtable.put("CZK", new Double("26.3"));
	}

	/**
	 * Recalculate value by Exchange Rates
	 * 
	 * @param currency
	 * @param value
	 * @return String
	 */
	public String changeValueForUSD(String currency, Double value) {
		if (!currency.equalsIgnoreCase("USD")) {
			if (this.hashtable.get(currency) != null) {
				if (value != null) {
					return "(USD " + formatter.format(value / this.hashtable.get(currency)) + ")";
				}
			} else {
				return "(This currency is not in the Exchange Rates)";
			}
		}
		return "";
	}
}
