package Persons;

import Items.Rentable;
//&begin [Messaging]
import Utilities.Message;
//&end [Messaging]

import java.util.ArrayList;
public class CustomerGold extends Customer{
//&begin [Messaging]
    CustomerGold(String name, ArrayList<Rentable> items, String ID, int credit, double amountSpent, ArrayList<Message> inbox, String password){
        super(name, password);
        setRentedItems(items);
        setID(ID);
        setCredit(credit);
        setInbox(inbox);
        setAmountSpent(amountSpent);
    }
//&end [Messaging]
    @Override
    public String getStrMembership(){
        return "gold";
    }

    @Override
    public void addCredit() {
        final int GOLD_CREDITS = 2;
        addCreditAmount(GOLD_CREDITS);
    }

    @Override
    public double memberDiscount(){
        final double GOLD_DISCOUNT = 0.85;
        return GOLD_DISCOUNT;
    }

    @Override
    public boolean libraryFull(){
        final int MAX_LIBRARY_GOLD = 5;
        return getRentedItems().size() >= MAX_LIBRARY_GOLD;
    }


}
