import java.util.*;
/**
 * Write a description of Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Rater {
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public String getID();
    public double getRating(String item);
    public int numRatings();
    public ArrayList<String> getItemsRated();
}
