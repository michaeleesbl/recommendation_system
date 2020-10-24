/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // dsafdasefault constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movies) {
            double rating = getAverageByID(id, minimalRaters);
            if (rating != 0.0) {
                Rating r = new Rating(id, rating);
                ans.add(r);
            }
        }
        return ans;
    }
    
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new YearAfterFilter(2000));
        System.out.println(movies.size());
        
        ArrayList<Rating> ratings = tr.getAverageRatings(1);
        ratings.sort(Comparator.comparing(Rating::getValue));
        System.out.println(ratings.size());
        /*for (String item : ratings) {
            String id = r.getItem();
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(id));
        }*/
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ans = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String id : movies) {
            double rating = getAverageByID(id, minimalRaters);
            if (rating != 0.0) {
                Rating r = new Rating(id, rating);
                ans.add(r);
            }
        }
        return ans;
    }
}