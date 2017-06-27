package cz.komix.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import cz.komix.file.ReadCsvFile;
import cz.komix.thread.ReadFileThread;

/**
 * Program PaymentTracker Keep data records of payments keep in memory and make
 * reports per 1 minute
 * 
 * @author stastny
 *
 */
public class PaymentTracker {

	public static ReadCsvFile csvFileReaderInstance = null;
	public static Logger log = null;
	public List<String[]> dataPaymentRepository = null;
	private static final String QUIT = "quit";
	private static final String spliter = " ";
	// pattern 3 char and space and number (first id 1-9, next 0-9)
	Pattern patternRow = Pattern
			.compile("([a-zA-Z]{3} (\\-)?(0|([1-9][0-9]*)))");

	/**
	 * Main of Payment tracker
	 * 
	 * @param args
	 *            [0] - name of csv file
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		PaymentTracker pTracker = new PaymentTracker();
		String fileName = "";
		if (args != null && args.length > 0 && args[0] != null
				&& args[0].length() > 0)
			fileName = args[0];
		pTracker.initDataPaymentRepository(fileName);
		ReadFileThread rft = new ReadFileThread(pTracker);
		rft.start();
		pTracker.readConsoleLine();
		rft.stop();
	}

	/**
	 * Init repository - get data of file or dimension DataPaymentRepository
	 * 
	 * @param fileName
	 */
	public void initDataPaymentRepository(String fileName) {
		if (fileName != null && fileName.length() > 0) {
			// file found - set primary data
			setDataPaymentRepository(getcsv(fileName));
		} else {
			// file not found set data by console
			setDataPaymentRepository(new ArrayList<String[]>());
		}
	}

	/**
	 * Methods for listen console data row
	 */
	public void readConsoleLine() {
		try {
			BufferedReader consoleBufferReader = new BufferedReader(
					new InputStreamReader(System.in));

			String line;
			do {
				line = consoleBufferReader.readLine();
				if (patternRow.matcher(line).matches()) {
					if (line.length() > 0 && !line.equalsIgnoreCase(QUIT)) {
						getDataPaymentRepository().add(line.split(spliter));
						displayData();
					}
				} else {
					System.out
							.println(line
									+ " - is no good format - you have to write 3 character and space and number");
				}

			} while (!line.equalsIgnoreCase(QUIT));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Get data from csv file
	 * 
	 * @param fileName
	 * @return List<String[]>
	 */

	public List<String[]> getcsv(String fileName) {
		ReadCsvFile instant = getCsvFileReaderInstance();
		List<String[]> retval = instant.readFile(fileName);
		return retval;
	}

	public void displayData() {
		DataPaymentDisplay.getInstance()
				.displayData(getDataPaymentRepository());
	}

	/**
	 * getter csv reader instance
	 * 
	 * @return ReadCsvFile
	 */
	public static ReadCsvFile getCsvFileReaderInstance() {
		if (csvFileReaderInstance == null) {
			csvFileReaderInstance = ReadCsvFile.getInstance();
		}
		return csvFileReaderInstance;
	}

	/**
	 * getter logger instance
	 * 
	 * @return
	 */
	public static Logger getLog() {
		if (log == null) {
			log = Logger.getLogger("PaymentTracker");
		}
		return log;
	}

	/**
	 * Getter dataPaymentRepository
	 * 
	 * @return the dataPaymentRepository
	 */
	public List<String[]> getDataPaymentRepository() {
		return dataPaymentRepository;
	}

	/**
	 * setter dataPaymentRepository
	 * 
	 * @param dataPaymentRepository
	 *            the dataPaymentRepository to set
	 */
	public void setDataPaymentRepository(List<String[]> dataPaymentRepository) {
		this.dataPaymentRepository = dataPaymentRepository;
	}

}
