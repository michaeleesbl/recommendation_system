
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int myMinutesMin;
    private int myMinutesMax;
    
    public MinutesFilter(int minutesMin, int minutesMax) {
        myMinutesMin = minutesMin;
        myMinutesMax = minutesMax;
    }
    
    @Override
	public boolean satisfies(String id) {
	    return MovieDatabase.getMinutes(id) >= myMinutesMin && MovieDatabase.getMinutes(id) <= myMinutesMax;
	}    
    
}
