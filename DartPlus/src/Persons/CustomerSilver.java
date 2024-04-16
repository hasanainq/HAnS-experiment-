package Persons;

import Items.Rentable;
//&begin Messaging
import Utilities.Message;
//&end Messaging

import java.util.ArrayList;

public class CustomerSilver extends Customer {
//&begin Messaging
    CustomerSilver(String name, ArrayList<Rentable> items, String ID, int credit, double amountSpent, ArrayList<Message> inbox, String password){
        super(name, password);
        setRentedItems(items);
        setID(ID);
        setCredit(credit);
        setInbox(inbox);
        setAmountSpent(amountSpent);
    }
//&end Messaging
    @Override
    public String getStrMembership(){
        return "silver";
    }

    @Override
    public void addCredit() {
        final int SILVER_CREDITS = 1;
        addCreditAmount(SILVER_CREDITS);
    }

    @Override
    public double memberDiscount(){
        final double SILVER_DISCOUNT = 0.9;
        return SILVER_DISCOUNT;
    }

    @Override
    public boolean libraryFull(){
        final int MAX_LIBRARY_SILVER = 3;
        return getRentedItems().size() >= MAX_LIBRARY_SILVER;
    }
}
