import java.util.*;

/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> list = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> list2 = new ArrayList<String>();
        
        for (int i = 0; i <= 10; i++) {
            list2.add(list.get(i));
        }
        
        return list2;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings();
        
        String id = webRaterID;
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        
        ArrayList<Rating> list = fr.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        int l = 0;
        
        System.out.println("<table>");
        for (Rating r : list) {
            System.out.println("<tr><td>" + MovieDatabase.getTitle(r.getItem()) +  "</td></tr>");
            l += 1;
            if (l > 10) break;
        }
        
        System.out.println("</table>");
    }
}
