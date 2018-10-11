import common.MovieRecommender;
import common.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BasicMovieRecommender implements MovieRecommender {

    private User user;
    private HashMap<String, List<String>> movies = new HashMap<>();
    private static final int CHILD_MIN_AGE = 0, TEEN_MIN_AGE = 13, ADULT_MIN_AGE = 17;
    public BasicMovieRecommender(User user)
    {
        this.user = user;
        this.movies.put("child", new ArrayList<String>(
                Arrays.asList("Shrek", "Coco", "The Incredibles")));

        this.movies.put("teen", new ArrayList<String>(
                Arrays.asList("The Avengers", "The Dark Knight", "Inception")));

        this.movies.put("adult", new ArrayList<String>(
                Arrays.asList("The Godfather", "Deadpool", "Saving Private Ryan")));
    }
    @Override
    public List<String> getRecommendedMovies() {
        int age = this.user.getAge();
        if (age < CHILD_MIN_AGE)
        {
            throw new IllegalArgumentException("Age cannot be below 0");
        }
        else if (age < TEEN_MIN_AGE)
        {
            return this.movies.get("child");
        }
        else if (age < ADULT_MIN_AGE)
        {
            return this.movies.get("teen");
        }
        else
        {
            return this.movies.get("adult");
        }
    }
}
