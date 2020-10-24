import java.util.*;
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private List<String> myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = Arrays.asList(directors.split("\\s*,\\s*"));
    }
    @Override
    public boolean satisfies(String id) {
        String directors = MovieDatabase.getDirector(id);
        for (String dir : myDirectors) {
            if (directors.contains(dir)) return true;
	}
	return false;
    }

}

