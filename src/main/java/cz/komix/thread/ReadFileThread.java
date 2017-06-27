package cz.komix.thread;

import cz.komix.main.PaymentTracker;

/**
 * This modul for thread used - time limit of intervals refresh Exchange Rates -
 * 1min
 *
 * @author stastny
 *
 */
public class ReadFileThread extends Thread {
	private static final long PRINT_INTERVAL_MILLIS = 60L * 1000L; // 1 minute

	private final Object conditionVariable = new Object();
	private final PaymentTracker paymentTracker;

	public ReadFileThread(PaymentTracker paymentTracker) {

		this.paymentTracker = paymentTracker;
	}

	@Override
	public void run() {

		while (true) {

			paymentTracker.displayData();

			synchronized (conditionVariable) {
				try {
					conditionVariable.wait(PRINT_INTERVAL_MILLIS);
				} catch (final InterruptedException ignored) {
				}
			}
		}
	}
}
