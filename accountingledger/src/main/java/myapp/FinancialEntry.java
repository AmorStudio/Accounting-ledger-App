package myapp;

// this is a record called Financial Entry
// Records are a concise way to create classes with data fields, automatically generating constructors, getters, 'equals' and hashcode methods
record FinancialEntry(String date, String description, double amount, String vendor) {

}
