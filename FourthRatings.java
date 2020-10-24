import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()) {
            if (!r.equals(me)) {
                // add dot_product(r, me) to list
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
}
