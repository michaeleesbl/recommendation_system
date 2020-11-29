import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());
        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        ArrayList<Rating> ratings = tr.getAverageRatings(35);
        System.out.println(ratings.size());
        ratings.sort(Comparator.comparing(Rating::getValue));
        
        for (Rating r : ratings) {
            String id = r.getItem();
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(id));
        }
    }
    
    public void printAverageRatingsByYearAndGenre() {
    }
    
    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        ArrayList<Rating> list = fr.getSimilarRatings("71", 20, 5);
        for (Rating item : list) {
            String movieName = MovieDatabase.getMovie(item.getItem()).getTitle();
            Double avgRating = item.getValue();
            System.out.println(movieName + ": " + avgRating);
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        GenreFilter gf = new GenreFilter("Mystery");
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("964", 20, 5, gf);
        for (Rating item : list) {
            String movieName = MovieDatabase.getMovie(item.getItem()).getTitle();
            Double avgRating = item.getValue();
            System.out.println(movieName + ": " + avgRating);
            System.out.println(MovieDatabase.getMovie(item.getItem()).getGenres());
        }
    }
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("120", 10, 2, df);
        for (Rating item : list) {
            String movieName = MovieDatabase.getMovie(item.getItem()).getTitle();
            Double avgRating = item.getValue();
            System.out.println(movieName + ": " + avgRating);
            System.out.println(MovieDatabase.getMovie(item.getItem()).getDirector());
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        GenreFilter gf = new GenreFilter("Drama");
        MinutesFilter mf = new MinutesFilter(80, 160);
        
        
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("168", 10, 3, af);
        for (Rating item : list) {
            String movieName = MovieDatabase.getMovie(item.getItem()).getTitle();
            int minutes = MovieDatabase.getMovie(item.getItem()).getMinutes();
            Double avgRating = item.getValue();
            System.out.println(movieName + " is " + minutes + " minutes long and has similary rating of " + avgRating);
            System.out.println(MovieDatabase.getMovie(item.getItem()).getGenres());
        }
    }
    
    public void printSimilarRatingsByYearAndMinutes() {
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

        YearAfterFilter yaf = new YearAfterFilter(1975);
        MinutesFilter mf = new MinutesFilter(70, 200);
        
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(mf);
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("314", 10, 5, af);
        for (Rating item : list) {
            String movieName = MovieDatabase.getMovie(item.getItem()).getTitle();
            int minutes = MovieDatabase.getMovie(item.getItem()).getMinutes();
            int year = MovieDatabase.getMovie(item.getItem()).getYear();
            Double avgRating = item.getValue();
            System.out.println("Movie name: " + movieName + "\tYear: " + year + "\tMinutes: " + minutes + "\tSimilarity Rating: " + avgRating);
        }
    }
}
