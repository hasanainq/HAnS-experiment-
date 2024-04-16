package Utilities;

public class RentHistoryItem {
    private double rentExpense;
    private String customerID;
    private String itemTitle;
    private int daysRented;
    private String itemID;
    private int score;
    private String review;

    public String getCustomerID() {
        return customerID;
    }
    public String getItemTitle() {
        return itemTitle;
    }
    public int getDaysRented() {
        return daysRented;
    }
    public String getItemID() {
        return itemID;
    }
    public int getScore() {
        return score;
    }
    public String getReview() { return review; }
    public double getRentExpense() {
        return rentExpense;
    }
    public RentHistoryItem(String itemTitle, double rentExpense, String customerID, int daysRented, String itemID, int score, String review) {
        this.itemTitle = itemTitle;
        this.rentExpense = rentExpense;
        this.customerID = customerID;
        this.daysRented = daysRented;
        this.itemID = itemID;
        this.score = score;
        this.review = review;
    }
    public RentHistoryItem(String itemTitle, double rentExpense, String customerID, int daysRented, String itemID, int score) {
        this.itemTitle = itemTitle;
        this.rentExpense = rentExpense;
        this.customerID = customerID;
        this.daysRented = daysRented;
        this.itemID = itemID;
        this.score = score;
    }
    public RentHistoryItem(String itemTitle, double rentExpense, String customerID, int daysRented, String itemID) {
        this.itemTitle = itemTitle;
        this.rentExpense = rentExpense;
        this.customerID = customerID;
        this.daysRented = daysRented;
        this.itemID = itemID;
    }
    public String toFileString(){
        return customerID + ";" + itemID + ";" + itemTitle + ";" + rentExpense;
    }

    @Override
    public String toString() {
        String str = "customerID='" + customerID + '\'' +
                ", daysRented=" + daysRented +
                ", itemID='" + itemID + '\'';

        if(this.score != 0) {
            str = str + ", score=" + score;
        }

        if(this.review != null){
            str = str + ", review='" + review;
        }

        return str;
    }
}
