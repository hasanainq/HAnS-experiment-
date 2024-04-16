package Items;

public class Song extends Rentable{
    private String artist;

    public Song(String title, String artist, double dailyRent, int year){
        super(title, dailyRent, year);
        this.artist = artist;
    }
    public String getArtist() { return artist; }
    public String toString(){
        String s = getID() + " : " + this.title + " - by " + getArtist() + ". Released in " + getYear() + ". Price: " + getDailyRent() + "SEK. "
                + "Rating: " + getAverageRating() + "/5"
                + " Status: ";
        if (getStatus()){
            s = s + "Available";
        }
        else{
            s = s + "Unavailable";
        }
        return s;
    }
}
