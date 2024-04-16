package Persons;

import IO.Tools;
import Items.Game;
import Items.Rentable;
import Items.Song;
import Utilities.NameEmptyException;
import Utilities.NegativeSalaryException;

import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    private String ID;
    private int birthyear;
    private String address;
    private double grossSalary;
    private double netSalary;
    private final String name;

    public Employee(String name, String address, int birthyear, double grossSalary){
        if (name.isEmpty() || name.equals(" ") ){
            throw new NameEmptyException("Empty name is not allowed.");
        } else {
            this.name = name;
        }
        if (grossSalary < 0) {
            throw new NegativeSalaryException("Salary cannot be less than zero.");
        } else {
            this.grossSalary = grossSalary;
        }
        this.address = address;
        this.birthyear = birthyear;
        this.ID = Tools.randomizeID();
    }
    public String getID(){ return ID; }
    public int getBirthyear(){ return birthyear; }
    public String getAddress() { return address; }
    public String getName() { return name; }
    public double getGrossSalary(){ return grossSalary; }
    public void setNetSalary(double netSalary){ this.netSalary = netSalary; }
    public double getNetSalary(){ return netSalary; }
    public String toString(){
        String s = getID() + " " + this.name + " - " + getBirthyear() + " (" + (Year.now().getValue() - getBirthyear()) + " ): " + "Gross salary: " + getGrossSalary() + " SEK";
        if(getNetSalary()!=0) s = s + " Net salary: " + getNetSalary() + " SEK";
        s = s + System.lineSeparator();
        return s;
    }
//&begin Data
    public Customer registerCustomer(ArrayList<Customer> customerArrayList, String name, String password, Scanner input){

        Customer customer = null;
        do{
            try{
                customer = new Customer(name, password);
            }catch (NameEmptyException e){
                name = Tools.getString("Enter the customer's name: ", input);
            }
        }while(customer == null);

        customerArrayList.add(customer);
        return customer;
    }
    public boolean removeCustomer(ArrayList<Customer> customerArrayList, Customer customer){ // method that removes customers
        return customerArrayList.remove(customer);
    }
    public Rentable registerItem(ArrayList<Rentable> itemsList, int type, String title, String genreArtist, double rent, int releaseYear) throws Exception {

        Rentable item;
        if(type == 1){
            item = new Game(title, genreArtist, rent, releaseYear);
        } else {
            item = new Song(title, genreArtist, rent, releaseYear);
        }
        itemsList.add(item);
        return item;
    }
    public boolean removeItem(ArrayList<Rentable> itemsList, Rentable item){
        return itemsList.remove(item);
    }
//&end Data
//&begin Search
    public Customer getCustomer(ArrayList<Customer> customerList, String ID){
        for (Customer customer : customerList) {
            if (customer.getID().equals(ID)) {
                return customer;
            }
        }
        return null;
    }
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
//&end Search
//&begin Membership
    public boolean upgradeCustomer(ArrayList<Customer> customers, Customer customer){

        if (customer instanceof CustomerSilver){
            customers.remove(customer);
//&begin Messaging
            customer = new CustomerGold(customer.getName(), customer.getRentedItems(), customer.getID(), customer.getCredit(), customer.getAmountSpent(), customer.getInbox(), customer.getPassword());
//&end Messaging
            customers.add(customer);
            return true;
        }
        else if (customer instanceof CustomerGold){
            customers.remove(customer);
//&begin Messaging
            customer = new CustomerPlatinum(customer.getName(), customer.getRentedItems(), customer.getID(), customer.getCredit(), customer.getAmountSpent(), customer.getInbox(), customer.getPassword());
//&end Messaging
            customers.add(customer);
            return true;
        }
        else if (customer instanceof CustomerPlatinum){
            //do nothing
            return false;
        }
        else if (customer != null) {
            customers.remove(customer);
//&begin Messaging
            customer = new CustomerSilver(customer.getName(), customer.getRentedItems(), customer.getID(), customer.getCredit(), customer.getAmountSpent(), customer.getInbox(), customer.getPassword());
//&end Messaging
            customers.add(customer);
            return true;
        }
        return false;
    }
//&end Membership
//&begin ViewCatalogue
    public String showItems(ArrayList<Rentable> itemsList){
        String itemStr = "";
        for (Rentable item : itemsList) {
            itemStr = itemStr.concat(item.toString() + System.lineSeparator());
        }
        return itemStr;
    }
//&end ViewCatalogue
//&begin ViewStats
    public String viewAllCustomer(ArrayList<Customer> customerList) {
        String cusStr = "";
        for (Customer customer : customerList) {
            cusStr = cusStr.concat(customer.toString() + System.lineSeparator());
        }
        return cusStr;
    }
    public String viewAllUpgRequest(ArrayList<Customer> upgradeRequests) {
        String upgReqStr = "";
        for (Customer customer : upgradeRequests) {
            if(upgradeRequests.contains(customer)){
                upgReqStr = upgReqStr.concat(customer.toString() + System.lineSeparator());
            }
        }
        return upgReqStr;
    }
//&end ViewStats
}
