package myapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

// Imports allow the ability to reference
// these classes and interfaces without having to use their fully qualified names ( with the package name)


// this java application is designed to manage financial transactions including deposits and payments
// it allows users to add,view and generate reports for their financial transactions
// Lets break the code down step by step


// This is my financial app class
    // it contains the 'main method' which serves as the entry point for the financial application
    // This is an introduction to the entire app
      public class FinancialApp {

    // the main method provides a user-friendly text-based menu for user interaction
    // it creates an instance of the ledger class, initializes a scanner for user input and provides a menu for user interaction
    public static void main(String[] args) throws IOException {
        Ledger ledger = new Ledger();
        // Instances (objects) are often used in conjunction with methods or
        // contain fields or variables that can be accessed and modified using dot notation
        // 
        Scanner scanner = new Scanner(System.in);
        char choice;


        //
        // it utilizes a 'do-while loop to continuously display the menu until the user chooses to exit
        // the menu offers the following options "D" to add a deposit
        do {
            System.out.println("Home Screen");
            System.out.println("D) Add Deposit");
            // "P" to make a payment
            System.out.println("P) Make Payment (Debit)");
            // "L" to view the ledger
            System.out.println("L) Ledger");
            // "X" to exit the application
            System.out.println("X) Exit");
            System.out.print("Please choose an option: ");

// Here I implemented a scanner to capture User input, and it reads the first character of the input as the Users choice
            choice = scanner.next().charAt(0);

// the switch statement is used to manage different user choices, determining the corresponding actions:
            switch (Character.toUpperCase(choice)) {
                case 'D' -> addDeposit(ledger, scanner);
                // if "D" is chosen, the 'addDeposit' method is invoked to add a deposit
                case 'P' -> makePayment(ledger, scanner);
                // if "P" is chosen, the 'makePayment' method is invoked to record a payment
                case 'L' -> showLedger(ledger, scanner);
                // if "L" is chosen the 'showLedger' method is invoked to view financial entries
                case 'X' -> System.out.println("Exiting the application.");
                default -> System.out.println("Invalid Option, Please try again ");
            }
            //  // The while (ledgerOption != 'X') condition means that the loop will continue running as long as the ledgerOption is not equal to 'X'.
        } while (choice != 'X');
    }

    // This is my 'addDeposit' Method
    // This method allows users to add a deposit entry to the ledger
    private static void addDeposit(Ledger ledger, Scanner scanner)
    // It takes 'ledger' the ledger object and 'Scanner' as parameters (usually within a method)

            throws IOException {
        // this method collects the Deposit information, Including the date,description, amount and Vendor name from the user
        String date = getUserInput(scanner, "Enter the deposit date (YYYY-MM-DD): ");
        String description = getUserInput(scanner, "Enter the deposit description: ");
        double amount = Double.parseDouble(getUserInput(scanner, "Enter the deposit amount: "));
        String vendor = getUserInput(scanner, "Enter the vendor name: ");

// it collects a new Financial Entry object with the provided information and add it to the ledger
        FinancialEntry entry = new FinancialEntry(date, description, amount, vendor);
        ledger.addEntry(entry);
        // it also calls the 'saveToCSV' method to save the entry to a csv File
        saveToCSV(entry);
        System.out.println("Deposit information saved.");
    }
    // allows users to make an entry (debit) in the ledger
    private static void makePayment(Ledger ledger, Scanner scanner)
    // it takes 'ledger' and 'scanner' as parameters similar to the addDeposit method
            throws IOException {
        // The method collects payment information, including the date, description and amount
        // it takes a 'Scanner' for user input and a prompt message as parameters
        String date = getUserInput(scanner, "Enter the payment date (YYYY-MM-DD): ");
        // Displays the prompt message to the user, instructing them on what to enter
        String description = getUserInput(scanner, "Enter the payment description: ");
        // it reads the user input as a string
        double amount = -Double.parseDouble(getUserInput(scanner, "Enter the payment amount: "));
        // converting a string to a double
        String vendor = getUserInput(scanner, "Enter the vendor name: ");
        // it collects a new Financial Entry object with the provided information and add it to the ledger
        FinancialEntry entry = new FinancialEntry(date, description, amount, vendor);
        ledger.addEntry(entry);
        // it also calls the 'saveToCSV' method to save the entry to a csv File
        saveToCSV(entry);
        // Success Message
        System.out.println("Payment information saved.");
    }

    private static String getUserInput(Scanner scanner, String prompt) {
        // displays the prompt message to the user, instructing them on what to enter.
        System.out.print(prompt);
        // reads the user's input as a string, and this string is returned as the result of the method.
        return scanner.next();
    }

    private static void displayEntries(List<FinancialEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }
        // prints the table header
        System.out.println("Date | Description | Amount | Vendor");
        // inside the loop, for each entry, it concatenates the values of entry.date(),
        // entry.description(), entry.amount(), and entry.vendor() to form a single line of text that represents the entry's details.
        for (FinancialEntry entry : entries) {
            System.out.println(entry.date() + " | " + entry.description() + " | $" + entry.amount() + " | " + entry.vendor());
        }
    }

// This is my 'showLedger' method
    // it is responsible for displaying the ledger screen,
// This provides another menu that allows the user to navigate various options related to the financial ledger

    private static void showLedger(Ledger ledger, Scanner scanner) throws IOException {
        char ledgerOption;
// a do-while loop is employed to repeatedly display the
// ledger screen until the user chooses to go back to the home screen by entering '0' or selects 'H' to return home
        do {
            // options in the ledger menu include viewing all entries ('A')
            // or displaying deposit entries ('D') and etc
            System.out.println("Ledger Screen");
            System.out.println("A) All - Display all entries");
            System.out.println("D) Deposits - Display deposit entries");
            System.out.println("P) Payments - Display payment entries");
            System.out.println("R) Reports - Access reports");
            System.out.println("0) Back - Go back to the home page");
            System.out.println("H) Home - Go back to the home page");
            System.out.print("Please choose an option: ");

            ledgerOption = scanner.next().charAt(0);
// Based on the users choice a
// switch statement is used to determine the corresponding Actions:

            switch (Character.toUpperCase(ledgerOption)) {
                case 'A' -> displayEntries(ledger.getAllEntries());
                /* if 'A' is chosen the 'displayEntries' method is invoked to show all ledger entries */
                case 'D' -> displayEntries(ledger.getDepositEntries());
                /* if d is chosen , deposit entries are displayed */
                case 'P' -> displayEntries(ledger.getPaymentEntries());
                /* if 'P' is chosen payment entries are displayed*/
                case 'R' -> showReports(ledger, scanner);
                /* if 'R' is chosen the 'showReports' method is invoked to access financial reports */
                case '0' -> {
                }
                /* if '0' is entered effectively returns to the home screen */
                case 'H' -> {
                    /* if 'H' is selected, the method terminates with a return statement , returning to the main loop in the main method */
                    return;
                }
                default -> System.out.println("Invalid ledger option. Please try again.");
            }
            // The while (ledgerOption != '0') condition means that the loop will continue running as long as the ledgerOption is not equal to '0'.
            // When the user enters '0,' the loop will exit, and the program will return to the home screen.
        } while (ledgerOption != '0');
    }

    // This is my 'showReports' method and inside it is responsible for displaying the reports' menu, allowing the user to access financial reports
    // similar to the 'show-ledger' screen
    private static void showReports(Ledger ledger, Scanner scanner) {
        char reportOption;
      // it employs a do-while loop to continuously display the reports menu until the user decides to go back to the ledger screen
        // in this case 'O' to return back to the ledger screen or 'H' to return to home
        do {
            // options in the reports menu include generating MonthToDate, PreviousMonth , YearToDate, PreviousYear and searching by Vendor options 1-5
            System.out.println("Reports");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back - Go back to the Ledger screen");
            System.out.println("H) Home - Go back to the home page");
            System.out.print("Please choose a report option: ");
         // so it is Home --> Ledger --> Reports --> Users Choice
            reportOption = scanner.next().charAt(0);
            // Depending on the selected report option , a Switch Statement is used to determine the corresponding actions
            // options 1-5 call specific methods to generate the corresponding financial reports.
            // if '0' is entered effectively returns back to the ledger screen menu
            switch (Character.toUpperCase(reportOption)) {
                case '1' -> generateMonthToDateReport(ledger);
                case '2' -> generatePreviousMonthReport(ledger);
                case '3' -> generateYearToDateReport(ledger);
                case '4' -> generatePreviousYearReport(ledger);
                case '5' -> searchByVendor(ledger, scanner);
                case '0' -> {
                }
                case 'H' -> {
                    return;
                }

                // if the user enters an invalid report option, an error message is displayed and the user is prompted to try again.
                default -> System.out.println("Invalid report option. Please try again.");
            }
            // Keep running as long as it is not prompted to go back to the home or ledger menu
        } while (reportOption != '0' && reportOption != 'H');
    }

    // this is my 'generatePreviousYearReport' method it generates a financial Report for the previous year.
    private static void generatePreviousYearReport(Ledger ledger) {

        Calendar currentDate = Calendar.getInstance();
        // We begin by getting the current year using the Calendar class
        int currentYear = currentDate.get(Calendar.YEAR);
        // extracting the current year from the current Date object

     // The 'entries' list is obtained from the ledger
        List<FinancialEntry> entries = ledger.getAllEntries();

     // two variables 'totalDeposits' and 'totalPayments are initialized to zero
        double totalDeposits = 0;
        double totalPayments = 0;

        //  in order to keep a running total of deposit amounts and payment amounts
        //  We need To calculate these totals accurately, you need to start with an initial value of zero.

// the previous year is calculated by subtracting 1 from the current year.

        String previousYearStr = Integer.toString(currentYear - 1);

// A for loop iterates through all the financial entries to check if each entry belongs to the previous year
        for (FinancialEntry entry : entries) {
            String entryYear = entry.date().substring(0, 4);
            // the substring represents the year
            if (entryYear.equals(previousYearStr)) {

                if (entry.amount() > 0) {
                    // it then updates the totalDeposit and totalPayments
                    totalDeposits += entry.amount();
                } else {
                    totalPayments += entry.amount();

                }
            }
        }
        // Finally it prints the previous years report including the total deposits and payments
        System.out.println("Previous Year Report (" + previousYearStr + "):");
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Payments: $" + (-totalPayments));
        // the Negative amount represents payments
    }

    // This method is declared as private static to limit its visibility and make it accessible from within the class.
// It calculates the YearToDate financial report.
    private static void generateYearToDateReport(Ledger ledger) {
        // It starts by creating a Calendar instance named currentDate.
        // A Calendar object provides various methods for manipulating dates and times.
        Calendar currentDate = Calendar.getInstance();

        // Retrieving Entries: It obtains a list of financial entries from the ledger object.
        // This list is stored in the entries variable, which contains all financial transactions.
        List<FinancialEntry> entries = ledger.getAllEntries();
        // Similar to the previous method it initializes 'entries', 'totalDeposits and 'totalPayments' variables
        // these variables are used to keep track of the total Deposit and Payment
        double totalDeposits = 0;
        double totalPayments = 0;

        // This line of code extracts the current year as a String using 'currentDate.get' and stores it in 'currentYearStr'
        String currentYearStr = String.valueOf(currentDate.get(Calendar.YEAR));

// For each entry this for loop extracts the year "YYYY"
        for (FinancialEntry entry : entries) {

            String entryYear = entry.date().substring(0, 4);
            // YYYY-MM-DD

            // Now it will check whether the extracted year matches the current year ('currentYearStr')
            if (entryYear.equals(currentYearStr)) {
                if (entry.amount() > 0) {
                    // if the amount is greater than 0, its considered a Deposit and the amount gets updated to 'totalDeposits'
                    totalDeposits += entry.amount();
                    // if the amount is less than 0, its considered a payment and the amount gets updated to 'totalPayments'
                } else {
                    totalPayments += entry.amount();
                }
            }
        }
        // Finally it displays the YearToDateReport
        System.out.println("Year-to-Date Report (" + currentYearStr + "):");
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Payments: $" + (-totalPayments));
    }

    // This is my 'generatePreviousMonthReport' Method
    private static void generatePreviousMonthReport(Ledger ledger) {
          // it starts obtaining the current date using the 'calendar' class
        // this calculates where we are right now, today's date
        Calendar currentDate = Calendar.getInstance();
         // then subtracts one month to move to the previous month
        currentDate.add(Calendar.MONTH, -1); // Move to the previous month.

        List<FinancialEntry> entries = ledger.getAllEntries();
        // Similar to Previous Methods it initializes 'entries', 'totalDeposits and 'totalPayments' variables
        double totalDeposits = 0;
        double totalPayments = 0;

        String previousMonthYear = String.valueOf(currentDate.get(Calendar.YEAR));

   // A loop iterates through the financial Entries to find entries matching previous month
        for (FinancialEntry entry : entries) {
            String entryMonthYear = entry.date().substring(0, 7);

            if (entryMonthYear.equals(previousMonthYear)) {
                if (entry.amount() > 0) {
                    // Depending on the type of entry (deposit or payment) it updates the 'totalDeposits' 'totalPayments' variables
                    totalDeposits += entry.amount();
                } else {
                    totalPayments += entry.amount();
                }
            }
        }
      // Finally, it prints the previous months report including the total deposits and total payments
        System.out.println("Previous Month Report (" + previousMonthYear + "):");
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Payments: $" + (-totalPayments));
    }
   // This my 'generateMonthToDate Report'
    private static void generateMonthToDateReport(Ledger ledger) {

        //it starts by obtaining the current date using the calendar class
        Calendar currentDate = Calendar.getInstance();

        List<FinancialEntry> entries = ledger.getAllEntries();
        double totalDeposits = 0;
        double totalPayments = 0;

        String currentMonthYear = String.valueOf(currentDate.get(Calendar.YEAR));

        for (FinancialEntry entry : entries) {
            String entryMonthYear = entry.date().substring(0, 7);

            if (entryMonthYear.equals(currentMonthYear)) {
                if (entry.amount() > 0) {
                    totalDeposits += entry.amount();
                } else {
                    totalPayments += entry.amount();
                }
            }
        }

        System.out.println("Month-to-Date Report (" + currentMonthYear + "):");
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Payments: $" + (-totalPayments));
    }

    private static void searchByVendor(Ledger ledger, Scanner scanner) {
        String vendorName = getUserInput(scanner, "Enter vendor name: ");
        // searchByVendor is a method in the Ledger class, now being implemented in this class
        // ,and it is intended to search for financial entries by a specific vendor's name.
        List<FinancialEntry> vendorEntries = ledger.searchByVendor(vendorName);
        displayEntries(vendorEntries);
    }

    // This is my 'saveToCSV' method
    // this method appends/add financial entries to the "Transactions.csv" file
    // it first takes 'FinancialEntry' object as a parameter
    private static void saveToCSV(FinancialEntry entry) throws IOException {
        // opens a FileWriter in append mode, meaning it will add new entries to existing files
        // Similar to the Arraylist
        try (FileWriter writer = new FileWriter("Transactions.csv", true))
        // Here we tell the 'FileWriter' to open the file in append mode
        // maintaining a running log of all financial entries
        {
            // it formats the entry's date, description, amount and vendor name with the plate symbol
            writer.append(entry.date()).append(" | ");

            // This part calls the description() method of the entry object, which is an instance of the FinancialEntry class.
            writer.append(entry.description()).append(" | ");
            writer.append(String.valueOf(entry.amount())).append(" | ");
            writer.append(entry.vendor()).append("\n");
            // This newline character serves as a line break, ensuring that the next piece of data
            // written to the file will appear on a new line.

        } catch (IOException e) {
            e.printStackTrace();
            // Some IOExceptions include: File not Found, Permission Denied or Disk Full
            // in that Case I used a Try/catch block for exception handling
            System.out.println("ERROR");


        }
    }
}