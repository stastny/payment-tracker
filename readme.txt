Exercises

Payment Tracker
Write a program that keeps a record of payments. Each payment includes a currency and an amount. Data should be kept in memory (please don’t introduce any database engines).

The program should output a list of all the currency and amounts to the console once per minute. The input can be typed into the command line with possibility to be automated in the future, and optionally also be loaded from a file when starting up.

Sample input:
USD 1000
HKD 100
USD -100
RMB 2000
HKD 200

Sample output:
USD 900
RMB 2000
HKD 300

Instruction for application:

Application is build as runnable jar.
java -jar PaymentTracker-1.0.0.jar path/fileName
Parameter filename is eligible. If not use this parametr, on the console is not data. If white data to console (format is 3 characters for Currency, space and number of value)
Examples
CZK 9245.50
EUR -5698

Parameter quit stop application

Maven build:
run maven install on project PaymentTracker