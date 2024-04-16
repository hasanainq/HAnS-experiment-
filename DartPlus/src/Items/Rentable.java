package Items;

import IO.Tools;
import Utilities.NameEmptyException;
import Utilities.NegativeRentException;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Rentable implements Comparable<Rentable>{

    protected String title;
    protected String ID;
    protected double dailyRent;
    protected boolean status;
    protected int year;
    protected LocalDate rentDate;
    protected LocalDate returnDate;
    protected double profit;
    protected int rentFrequency;

    protected ArrayList<Integer> ratings;
    protected ArrayList<String> reviews;

    public Rentable(String title, double dailyRent, int year){
        if (title.isEmpty() || title.equals(" ") ){
            throw new NameEmptyException("Empty title is not allowed.");
        } else {
            this.title = title;
        }
        if (dailyRent < 0){
            throw new NegativeRentException("Negative rent is not allowed.");
        } else {
            this.dailyRent = dailyRent;
        }
        this.status = true;
        this.ratings = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.year = year;

        this.ID = Tools.randomizeID();

        this.rentDate = LocalDate.parse("2020-01-01");
        this.returnDate = LocalDate.parse("2020-01-01");
        this.profit = 0;
        this.rentFrequency = 0;
    }
    public String getTitle() {
        return title;
    }
    public String getID (){ return ID; }
    public double getDailyRent(){ return dailyRent; }
    public void setStatus (boolean status){ this.status = status; }
    public boolean getStatus(){ return status; }
    public int getYear() {
        return year;
    }
    public void setRentDate(String rentADate){ rentDate = LocalDate.parse(rentADate); }
    public void setAutomaticRentDate(){ rentDate = LocalDate.now(); }
    public LocalDate getRentDate(){ return rentDate; }
    public void setReturnDate(String returnADate){ returnDate = LocalDate.parse(returnADate); }
    public void setAutomaticReturnDate(){ returnDate = LocalDate.now(); }
    public LocalDate getReturnDate(){ return returnDate; }
    public double getProfit() {
        return profit;
    }
    public int getRentFrequency() {
        return rentFrequency;
    }
    public void addRentFrequency(){
        this.rentFrequency += 1;
    }
//&begin Ratings
    public void addRating(int rating) {
        this.ratings.add(rating);
    }
    public double getAverageRating() {
        int sum = 0;
        int size = ratings.size() > 0 ? ratings.size() : 1;
        for(Integer value : ratings){
            sum += value;
        }
        return ((double)sum / size);
    }
//&end Ratings
//&begin Review
    public void addReview(String review){
        this.reviews.add(review);
    }
//&end Review
    public String getGenre(){
        System.out.println("WRONG GENRE");
        return "WRONG GENRE";
    }

    @Override
    public int compareTo(Rentable o) {
        if(this.getAverageRating() < o.getAverageRating()){
            return -1;
        } else if(this.getAverageRating() == o.getAverageRating()){
            return 0;
        } else {
            return 1;
        }
    }
}
