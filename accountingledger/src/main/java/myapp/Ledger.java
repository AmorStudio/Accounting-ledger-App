package myapp;

import java.util.ArrayList;
import java.util.List;

// this is the ledger class
// it represents the ledger for storing financial entries like a bookkeeper
// it maintains a list of FinancialEntry objects
class Ledger {


    private final List<FinancialEntry> entries;

    public Ledger() {

        //  In the Ledger class constructor it is initialized as an ArrayList:
        this.entries = new ArrayList<>();
        // This is important because it provides a clean slate
        // it starts with an empty list of financial entries that can grow or shrink

        // Throughout the code, you'll see methods like addEntry:
        // when you add a deposit or payment , it is added to the ArrayList.

    }


    // this is my addEntry method
    // this method allows you to add a financial entry to the ledger
    public void addEntry(FinancialEntry entry) {
        // parameter of the FinancialEntry Class
        entries.add(entry);
        // object
    }

    /* The getAllEntries , getDepositEntries and getPaymentEntries Methods below will allow me to successfully retrieve the user entries
    if the user's deposit amount is more than 0 or payment is less than 0 */
    public List<FinancialEntry> getAllEntries() {
        // It returns a List of FinancialEntry objects
        return entries;
        // entries is an instance variable of the Ledger class that holds all the financial entries.
    }

    public List<FinancialEntry> getDepositEntries() {
        List<FinancialEntry> deposits = new ArrayList<>();
        // Inside this method, a new ArrayList named deposits is created to store the deposit entries.
        for (FinancialEntry entry : entries) {
            // iterates through the entries list (which holds all financial entries)
            if (entry.amount() > 0) {
                deposits.add(entry);
            }
        }
        return deposits;
    }


    public List<FinancialEntry> getPaymentEntries() {
        List<FinancialEntry> payments = new ArrayList<>();

        for (FinancialEntry entry : entries) {
            if (entry.amount() < 0) {
                payments.add(entry);
            }
        }
        return payments;
    }

     /* Querying Entries: Methods like getAllEntries, getDepositEntries, getPaymentEntries, and searchByVendor
     use the ArrayList to filter, retrieve, and return specific subsets/elements of financial entries based on user queries */

    // this is my SearchByVendor method
    // this method takes a vendor name and returns a list of financial Entries for that Vendor
    public List<FinancialEntry> searchByVendor(String vendorName) {
        // Method is declared and takes a String called VendorName
        List<FinancialEntry> vendorEntries = new ArrayList<>();
        for (FinancialEntry entry : entries) {
            // checks whether the vendor name of the entry matches the 'vendorName' provided in case-insensitive manner
            if (entry.vendor().equalsIgnoreCase(vendorName)) {
                // comparing the two
                vendorEntries.add(entry);
            }
        }
        return vendorEntries;
    }
}
