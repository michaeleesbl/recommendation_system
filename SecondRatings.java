/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // dsafdasefault constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        int numRaters = 0;
        double scores = 0.0;
        for (EfficientRater r : myRaters) {
            if (r.hasRating(id)) {
                numRaters++;
                scores += r.getRating(id);
            }
        }
        if (numRaters < minimalRaters) return 0.0;
        return scores / numRaters;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ans = new ArrayList<Rating>();
        for (Movie m : myMovies) {
            String id = m.getID();
            double rating = getAverageByID(id, minimalRaters);
            if (rating != 0.0) {
                Rating r = new Rating(id, rating);
                ans.add(r);
            }
        }
        for (Rating r : ans) {
            System.out.println(r.getValue());
        }
        return ans;
    }
    
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) return m.getTitle();
        }
        return "Movie not in the list";
    }
    
    public String getID(String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) return m.getID();
        }
        return "NO SUCH TITLE";
    }
}