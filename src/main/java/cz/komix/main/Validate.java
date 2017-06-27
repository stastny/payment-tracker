package cz.komix.main;

import java.util.regex.Pattern;

/**
 * Validate data. The object is singleton.
 * 
 * @author stastny
 *
 */
public class Validate {

	// define instance Validate
	private static final Validate INSTANCE = new Validate();

	public static Validate getInstance() {
		return INSTANCE;
	}

	// pattern 3 char and space and number (first id 1-9, next 0-9)
	Pattern patternRow = Pattern
			.compile("([a-zA-Z]{3} (\\-)?(0|([1-9][0-9]*)))");
	
	private Pattern patternValue = Pattern.compile("(\\-)?(0|([1-9][0-9]*))");

	/**
	 * Controll data by pattern.
	 * @param line
	 * @return
	 * True = Data is OK
	 * False = Data is not validate.
	 */
	public boolean isValidate(String line){
		return patternRow.matcher(line).matches();
	}

	/**
	 * Controll value by pattern.
	 * @param line
	 * @return
	 * True = Value is OK
	 * False = Value is not validate.
	 */
	public boolean isValidateValue(String value){
		return patternValue.matcher(value).matches();
	}

}
