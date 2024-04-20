package Persons;

import IO.Tools;
import Items.Game;
import Items.Rentable;
import Items.Song;
//&begin [Messaging]
import Utilities.Message;
//&end [Messaging]
import Utilities.RatingsComparator;
import Utilities.RentHistoryItem;
import Utilities.YearComparator;

import java.util.ArrayList;
import java.util.Collections;

public class Customer {

    private String ID;
    private String name;
    private int credit;
    private String password;
    private double amountSpent;

    private ArrayList<Rentable> rentedItems = new ArrayList<>();
//&begin [Messaging]
    private ArrayList<Message> inbox = new ArrayList<>();
//&end [Messaging]
    Customer(String name, String password){
        this.name = name;
        this.ID = Tools.randomizeID();
        this.amountSpent = 0;
        this.password = password;
    }
    public String getID(){ return ID; }
    public void setID(String ID){
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public int getCredit() { return credit; }
    public void setCredit(int credit) {
        this.credit = credit;
    }
    public void addCreditAmount(int credits){
        this.credit+= credits;
    }
    public void addCredit() {
        final int REG_CREDITS = 0;
        addCreditAmount(REG_CREDITS);
    }
    public void removeCredit(int remove){
        this.credit -= remove;
    }
    public double memberDiscount(){
        final double REG_DISCOUNT = 1;
        return REG_DISCOUNT;
    }
    public boolean libraryFull(){
        final int MAX_LIBRARY_REG = 1;
        return getRentedItems().size() >= MAX_LIBRARY_REG;
    }
    public String getPassword() {
        return password;
    }
    public double getAmountSpent() {
        return amountSpent;
    }
    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }
    public void addSpent(double spent){
        this.amountSpent = this.amountSpent + spent;
    }
    public ArrayList<Rentable> getRentedItems() {
        return rentedItems;
    }
    public void setRentedItems(ArrayList<Rentable> items){
        this.rentedItems = items;
    }
    public void addToLibrary(Rentable item){
        rentedItems.add(item);
    }
    public String toString(){
        return getID() + " : " + this.name + System.lineSeparator();
    }
//&begin [Messaging]
    public ArrayList<Message> getInbox() {
        return inbox;
    }
    public void setInbox(ArrayList<Message> inbox) {
        this.inbox = inbox;
    }
    public Customer getCustomer(ArrayList<Customer> customerList, String ID){
        for (Customer customer : customerList) {
            if (customer.getID().equals(ID)) {
                return customer;
            }
        }
        return null;
    }
    public boolean sendMessage(ArrayList<Customer> customerList, String message, String recipientID, Customer sender){
        Customer recipient = getCustomer(customerList, recipientID);
        if (recipient == null){
            return false;
        } else {
            recipient.addMessage(new Message(message, sender.getID()));
            return true;
        }
    }
    public String viewInbox(Customer customer){
        return customer.viewInbox();
    }
    public String viewUnread(Customer customer){
        return customer.viewUnread();
    }
    public void removeMessage(int index, Customer customer){
        if(index <= customer.getInboxSize()) {
            customer.removeMessage(index);
        }
    }
    public String viewInbox(){
        String inboxStr = "";
        for(Message message : inbox){
            inboxStr = inboxStr.concat((inbox.indexOf(message) + 1) + ". " + message + System.lineSeparator());
        }
        return inboxStr;
    }
    public void removeMessage(int index){
        inbox.remove(index);
    }
    public String viewUnread(){
        String inboxStr = "";
        for(Message message : inbox){
            if(!message.isRead()){
                inboxStr = inboxStr.concat((inbox.indexOf(message) + 1) + ". " + message + System.lineSeparator());
            }
        }
        return inboxStr;
    }
    public void addMessage(Message message){
        this.inbox.add(message);
    }
    public int getInboxSize(){ return inbox.size(); }
//&end [Messaging]
//&begin [ViewCatalogue]
    public String showItems(ArrayList<Rentable> itemsList, int itemType, int selectionSorting, String optionalGenreOrYear){
        String itemStr = "";
        if (itemType == 1) {
            if(selectionSorting == 1) {
                return showItemsOfType(itemsList, 1);
            } else if (selectionSorting == 2) {
                return viewGamesByGenre(itemsList, optionalGenreOrYear);
            } else if (selectionSorting == 3){
                itemsList.sort(new RatingsComparator());
                Collections.reverse(itemsList);
                for (Rentable game: itemsList) {
                    if(game instanceof Game) {
                        itemStr = itemStr.concat(game.toString() + System.lineSeparator());
                    }
                }
                return itemStr;
            } else if (selectionSorting == 4){
                itemsList.sort(new YearComparator());
                Collections.reverse(itemsList);
                for (Rentable game: itemsList) {
                    if(game instanceof Game) {
                        itemStr = itemStr.concat(game.toString() + System.lineSeparator());
                    }
                }
                return itemStr;
            }
        } else {
            if(selectionSorting == 1) {
                return showItemsOfType(itemsList, 2);
            } else if (selectionSorting == 2){
                try {
                    int year = Integer.parseInt(optionalGenreOrYear);
                    return viewSongByYear(itemsList, year);
                } catch (Exception exception){
                    return ("Year entered in wrong format (should be YYYY), aborting rent process");
                }
            } else if (selectionSorting == 3){
                itemsList.sort(new RatingsComparator());
                Collections.reverse(itemsList);
                for ( Rentable song: itemsList) {
                    if(song instanceof Song) {
                        itemStr = itemStr.concat(song.toString() + System.lineSeparator());
                    }
                }
                return itemStr;
            } else if (selectionSorting == 4){
                itemsList.sort(new YearComparator());
                Collections.reverse(itemsList);
                for ( Rentable song: itemsList) {
                    if(song instanceof Song) {
                        itemStr = itemStr.concat(song.toString() + System.lineSeparator());
                    }
                }
                return itemStr;
            }
        }
        return "Could not find any items";
    }
    public String viewGamesByGenre(ArrayList<Rentable> itemsList, String genre){
        String gameStr = "";
        for (Rentable game : itemsList) {
            if(game instanceof Game) {
                if (game.getGenre().equals(genre)) {
                    gameStr = gameStr.concat(game.toString() + System.lineSeparator());
                }
            }
        }
        return gameStr;
    }
    public String viewSongByYear(ArrayList<Rentable> itemsList, int year){
        String songStr = "";
        for (Rentable song : itemsList) {
            if(song instanceof Song) {
                if (song.getYear() == year) {
                    songStr = songStr.concat(song.toString() + System.lineSeparator());
                }
            }
        }
        return songStr;
    }
    public String showItemsOfType(ArrayList<Rentable> itemsList, int type){

        String itemStr = "";
        if(type == 1) {
            for (Rentable rentable : itemsList) {
                if (rentable instanceof Game) {
                    itemStr = itemStr.concat(rentable.toString() + System.lineSeparator());
                }
            }
        } else {
            for (Rentable rentable : itemsList) {
                if (rentable instanceof Song) {
                    itemStr = itemStr.concat(rentable.toString() + System.lineSeparator());
                }
            }
        }
        return itemStr;
    }
    public String viewRented(){
        String rentedItems = "";
        for ( Rentable item : this.rentedItems) {
            rentedItems = rentedItems.concat(item.toString() + System.lineSeparator());
        }
        return rentedItems;
    }
//&end [ViewCatalogue]
//&begin [Search]
    public Rentable findItem(ArrayList<Rentable> itemsList, String ID){
        ArrayList<Rentable> array;
        int i;
        array = itemsList;


        for(i = 0; i < array.size(); i++){
            if(array.get(i).getID().equals(ID)){
                return array.get(i);
            }
        }
        return null;
    }
//&end [Search]
//&begin [Membership]
public String getStrMembership(){
    return "none";
}
    public void requestUpgrade(ArrayList<Customer> upgradeRequests, Customer customer){
        upgradeRequests.add(customer);
    }
//&end [Membership]
//&begin [RentItem]
    public boolean rentItem(Rentable item, String rentDate){
        boolean wasRented = false;
        if (item!= null && item.getStatus() && !libraryFull()){
            try {
                item.setRentDate(rentDate);
            } catch (Exception e) {
                System.out.println("ERROR: Wrong format, assuming rent date is today.");
                item.setAutomaticRentDate();
            }
            addToLibrary(item);
            wasRented = true;
        }
        return wasRented;
    }
//&end [RentItem]
//&begin [Payment]
//&begin [Ratings]
//&begin [Review]
    public RentHistoryItem returnItem(String itemID, String review, int rating, String returnDate){
        int CREDIT_COST = 5;
        Rentable item;
        boolean reviewLeft = false;
        boolean ratingLeft = false;
        boolean returned = false;
        long calcDaysResult = 0;
        double rent = 0;
        String itemTitle = "";
        for (int i = 0; i < rentedItems.size(); i++) {
            if (rentedItems.get(i).getID().equals(itemID)) {
                item = rentedItems.get(i);
                try {
                    item.setReturnDate(returnDate);
                } catch (Exception e) {
                    System.out.println("ERROR: Wrong format, assuming return date is today.");
                    item.setAutomaticReturnDate();
                }
                calcDaysResult = Tools.calcDays(item);
                rent = calcDaysResult * memberDiscount() * item.getDailyRent();
                if (getCredit() >= CREDIT_COST){
                    rent = 0;
                    removeCredit(CREDIT_COST);
                }
                addCredit();
                if (rating != 0){
                    item.addRating(rating);
                    if (!review.equals("")) {
                        item.addReview(review);
                        reviewLeft = true;
                    }
                    ratingLeft = true;
                }
                rentedItems.remove(item);
                addSpent(rent);
                itemTitle = item.getTitle();
                item.setStatus(true);
                item.addRentFrequency();
                returned = true;
            }
        }

        if (reviewLeft) {
            return (new RentHistoryItem(itemTitle, rent, ID, (int)calcDaysResult, itemID, rating, review));
        }
        else if (ratingLeft) {
            return (new RentHistoryItem(itemTitle, rent, ID, (int) calcDaysResult, itemID, rating));
        }
        else if (returned){
            return (new RentHistoryItem(itemTitle, rent, ID, (int)calcDaysResult, itemID));
        } else {
            return null;
        }
    }
//&end [Review]
//&end [Ratings]
//&end [Payment]
}
