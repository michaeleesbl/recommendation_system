import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord record : fr.getCSVParser()) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            movies.add(new Movie(id, title, year, genres, director, country, poster, minutes));
        }
        return movies;
    }
    
    public void testLoadMovies() {
        List<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println(movies.size());
        //for (Movie m : movies) {
        //    System.out.println(m);
        //}
        int numComedies = 0;
        int numLt150 = 0;
        for (Movie m : movies) {
            if (m.getGenres().contains("Comedy")) numComedies++;
            if (m.getMinutes() > 150) numLt150++;
        }
        System.out.println("Number of comedies: " + numComedies);
        System.out.println("Number of movies longer than 150 minutes: " + numLt150);
        
        Map<String, Integer> dirsMap = new HashMap<String, Integer>();
        for (Movie m : movies) {
            String director = m.getDirector();
            String[] directors = director.split(",");
            for (String d : directors) {
                if (!dirsMap.containsKey(d)) {
                    dirsMap.put(d, 1);
                } else {
                    dirsMap.put(d, dirsMap.get(d) + 1);
                }
            }
        }
        
        int maxNum = Collections.max(dirsMap.values());
        System.out.println("Max number of movies by any director: " + maxNum);
        for (String dir : dirsMap.keySet()) {
            if (dirsMap.get(dir) == maxNum) {
                System.out.println(dir + " directed " + maxNum + " films.");
            }
        }
    }
    
    public ArrayList<EfficientRater> loadRaters(String filename) {
        FileResource fr = new FileResource(filename);
        
        Map<String, HashMap<String, Double>> map = new HashMap<String, HashMap<String, Double>>();
        for (CSVRecord rec : fr.getCSVParser()) {
            String id = rec.get("rater_id");
            String movie = rec.get("movie_id");
            double rating = Double.parseDouble(rec.get("rating"));
            
            if (!map.containsKey(id)) {
                HashMap<String, Double> movies = new HashMap<String, Double>();
                movies.put(movie, rating);
                map.put(id, movies);
            } else {
                HashMap<String, Double> movies = map.get(id);
                if (!movies.containsKey(movie)) movies.put(movie, rating);
                map.put(id, movies);
            }
        }
        
        ArrayList<EfficientRater> raters = new ArrayList<EfficientRater>();
        for (String id : map.keySet()) {
            HashMap<String, Double> ratings = map.get(id);
            EfficientRater r = convertToRater(id, ratings);
            raters.add(r);
        }
        return raters;
    }
        
    private EfficientRater convertToRater(String id, HashMap<String, Double> ratings) {
        EfficientRater r = new EfficientRater(id);
        
        for (String movie : ratings.keySet()) {
            if (!r.hasRating(movie)){
                r.addRating(movie, ratings.get(movie));
            }
        }
        
        return r;
    }
    
    public void testLoadRaters() {
        ArrayList<EfficientRater> raters = loadRaters("data/ratings.csv");
        System.out.println(raters.size());
        //for (Rater r : raters) {
        //    System.out.println(r.getID() + ": " + r.numRatings());
        //    ArrayList<String> itemsRated = r.getItemsRated();
        //    for (String item : itemsRated) {
        //        System.out.println(item + ": " + r.getRating(item));
        //    }
        //}
        String raterWanted = "193";
        for (EfficientRater r : raters) {
            if (r.getID().equals(raterWanted)) System.out.println(raterWanted + " has " + r.numRatings() + " ratings.");
        }
        
        ArrayList<Integer> numRatings = new ArrayList<Integer>();
        int max = 0;
        for (EfficientRater r : raters) {
            if (r.numRatings() > max) max = r.numRatings();
            numRatings.add(r.numRatings());
        }
        
        int numRatersWithMax = 0;
        for (EfficientRater r : raters) {
            if (r.numRatings() == max) {
                System.out.println(r.getID() + " has " + max + " ratings.");
                numRatersWithMax++;
            }
        }
        System.out.println(numRatersWithMax + " have max number of ratings.");
        
        String item = "1798709";
        int numTimesRated = 0;
        for (EfficientRater r : raters) {
            if (r.hasRating(item)) numTimesRated++;
        }
        System.out.println(item + " was rated " + numTimesRated + " times.");
        
        int numItemsRated = 0;
        ArrayList<String> itemsRated = new ArrayList<String>();
        for (EfficientRater r : raters) {
            itemsRated.addAll(r.getItemsRated());
        }
        Set<String> uniqueItemsRated = new HashSet<String>(itemsRated);
        System.out.println(uniqueItemsRated.size());
    }
}
