import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    
    public ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        //RaterDatabase.initialize("ratings.csv");
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater compareRater = RaterDatabase.getRater(id);
        for(Rater rater : raters) {
            double product = 0.0;
            if (!rater.getID().equals(id)) {
                product = dotProduct(compareRater, rater);
                if (product > 0.0) {
                    Rating rating = new Rating(rater.getID(), product);
                    ratings.add(rating);
                }
            }
        }
        //for (Rating r : ratings) {
        //    System.out.println(r);
        //}
        Collections.sort(ratings);
        Collections.reverse(ratings);
        return ratings;
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        int numRaters = 0;
        double scores = 0.0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters(); //("ratedmoviesfull.csv");
        for (Rater r : myRaters) {
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
    
    private double dotProduct(Rater me, Rater r) {
        
        ArrayList<String> items = MovieDatabase.filterBy(new TrueFilter());        
        double product = 0.0;
        String meID = me.getID();
        String rID = r.getID();
        Rater meAfter = RaterDatabase.getRater(meID);
        Rater rAfter = RaterDatabase.getRater(rID);
        for (String item : items) {
            if (meAfter.hasRating(item) && rAfter.hasRating(item)) {
                double meScaledScore = meAfter.getRating(item) - 5.0;
                double rScaledScore = rAfter.getRating(item) - 5.0;
                double temp = meScaledScore * rScaledScore;
                product += temp;
            }

        }
        return product;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> raterRatings = getSimilarities(id); // list of similar raters to rater associated with input parameter id
        ArrayList<Rating> MovieRatings = new ArrayList<Rating>();
        ArrayList<String> items = MovieDatabase.filterBy(new TrueFilter());

        for (String item : items) {
            double sum = 0.0;
            int count = 0;
            for (int k = 0; k < numSimilarRaters; k++) {
                Rating r = raterRatings.get(k);
                String raterID = r.getItem();
                Rater simRater = RaterDatabase.getRater(raterID);

                if (simRater.hasRating(item)) {
                    count++;
                    double score = simRater.getRating(item);// get similarity weight
                    double res = r.getValue() * score;// multiply weight by rating for movie
                    sum += res;// add product to scores
                }
            }
            
            if (count >= minimalRaters && !RaterDatabase.getRater(id).hasRating(item)) {
                double w_average = sum / count;// divide scores by numraters
                Rating rating = new Rating(item, w_average);
                MovieRatings.add(rating);// create new rating object with id of movie and score & add rating to movieratings
            }
        }
        Collections.sort(MovieRatings, Collections.reverseOrder());
        return MovieRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> raterRatings = getSimilarities(id); // list of similar raters to rater associated with input parameter id
        ArrayList<Rating> MovieRatings = new ArrayList<Rating>();
        ArrayList<String> items = MovieDatabase.filterBy(f);

        for (String item : items) {
            double sum = 0.0;
            int count = 0;
            for (int k = 0; k < numSimilarRaters; k++) {
                Rating r = raterRatings.get(k);
                String raterID = r.getItem();
                Rater simRater = RaterDatabase.getRater(raterID);

                if (simRater.hasRating(item)) {
                    count++;
                    double score = simRater.getRating(item);// get similarity weight
                    double res = r.getValue() * score;// multiply weight by rating for movie
                    sum += res;// add product to scores
                }
            }
            
            if (count >= minimalRaters){// && !RaterDatabase.getRater(id).hasRating(item)) {
                double w_average = sum / count;// divide scores by numraters
                Rating rating = new Rating(item, w_average);
                MovieRatings.add(rating);// create new rating object with id of movie and score & add rating to movieratings
            }
        }
        Collections.sort(MovieRatings, Collections.reverseOrder());
        return MovieRatings;
    }
    
    
}
