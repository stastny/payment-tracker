package cz.komix.test;

import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import cz.komix.currency.CurrencyValue;

public class TestExchangeRates {

	@Test
	public void testExchangeRatesUSD() {
		String rates = CurrencyValue.getInstance().changeValueForUSD("USD", new Double("10000.0"));
		ReflectionAssert.assertReflectionEquals(rates, "");
	}
	
	@Test
	public void testExchangeRatesRKD() {
		String rates = CurrencyValue.getInstance().changeValueForUSD("RKD", new Double("45.6"));
		ReflectionAssert.assertReflectionEquals(rates, "(This currency is not in the Exchange Rates)");
	}

}
