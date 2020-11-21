import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println("Number of movies: " + sr.getMovieSize());
        System.out.println("Number of raters: " + sr.getRaterSize());
        
        ArrayList<Rating> ratings = sr.getAverageRatings(20);
        ratings.sort(Comparator.comparing(Rating::getValue));
        
        for (Rating r : ratings) {
            String id = r.getItem();
            System.out.println(r.getValue() + "\t" + sr.getTitle(id));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String id = sr.getID("Vacation");
        ArrayList<Rating> ratings = sr.getAverageRatings(1);
        for (Rating r : ratings) {
            if (r.getItem().equals(id)) System.out.println(r.getValue());
        }
    }
}
