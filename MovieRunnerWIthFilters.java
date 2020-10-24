import java.util.*;
/**
 * Write a description of MovieRunnerWIthFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWIthFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        ArrayList<Rating> ratings = tr.getAverageRatings(1);
        System.out.println(ratings.size());
        ratings.sort(Comparator.comparing(Rating::getValue));
        
        for (Rating r : ratings) {
            String id = r.getItem();
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(id));
        }
    }
    
    public void printAverageRatingsByYear() {
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        YearAfterFilter yaf = new YearAfterFilter(2000);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, yaf);
        System.out.println(ratings.size());
        for (Rating r : ratings) {
            String id = r.getItem();
            System.out.println("Rating: " + r.getValue());
            System.out.println(MovieDatabase.getYear(id));
            System.out.println(MovieDatabase.getTitle(id));
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        GenreFilter gf = new GenreFilter("Crime");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, gf);
        System.out.println(ratings.size());
        for (Rating r : ratings) {
            String id = r.getItem();
            System.out.println("Rating: " + r.getValue());
            System.out.println(MovieDatabase.getGenres(id));
            System.out.println(MovieDatabase.getTitle(id));
        }        
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        MinutesFilter mf = new MinutesFilter(110, 170);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, mf);
        System.out.println(ratings.size());
        for (Rating r : ratings) {
            String id = r.getItem();
            System.out.println("Rating: " + r.getValue());
            System.out.println(MovieDatabase.getMinutes(id));
            System.out.println(MovieDatabase.getTitle(id));
        }        
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        DirectorsFilter df = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, df);
        System.out.println(ratings.size());
        for (Rating r : ratings) {
            String id = r.getItem();
            System.out.println("Rating: " + r.getValue());
            System.out.println(MovieDatabase.getTitle(id));
            System.out.println(MovieDatabase.getDirector(id));
        }            
    }
}
