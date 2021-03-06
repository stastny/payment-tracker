package cz.komix.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cz.komix.main.Validate;

/**
 * Reader csv files and parsing from List. The object is singleton.
 * 
 * @author stastny
 *
 */
public class ReadCsvFile {

	// define instance ReadCvsFile
	private static final ReadCsvFile INSTANCE = new ReadCsvFile();

	public static ReadCsvFile getInstance() {
		return INSTANCE;
	}

	/**
	 * Method for read data from csv file
	 * 
	 * @param csvName
	 * @return
	 */
	public List<String[]> readFile(String csvName) {
		Logger.getLogger("ReadCsvFile").info("Read csv file...");
		// line for data
		String line = "";
		// define splitter
		String cvsSplitBy = " ";
		// define retval
		List<String[]> retval = new ArrayList<String[]>();
		// reading data from file
		try {
			BufferedReader bufReader = new BufferedReader(new FileReader(
					csvName));

			while ((line = bufReader.readLine()) != null) {
				// controll data
				if (Validate.getInstance().isValidate(line)) {
					// split and save to List
					retval.add(line.split(cvsSplitBy));
				} else {
					Logger.getLogger("ReadCsvFile").info(
							line + " - this row is a bug");
				}

			}
			// close buffer reader
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.getLogger("ReadCsvFile").info(
				"return list with row and column of csv file");
		// return list with row and column of csv file
		return retval;
	}

}
