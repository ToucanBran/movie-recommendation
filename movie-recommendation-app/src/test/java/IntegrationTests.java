import common.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class IntegrationTests {

    HashMap<String, List<String>> movies = new HashMap<>();

    @Before
    public void Setup() {
        this.movies.put("child", new ArrayList<>(
                Arrays.asList("Shrek", "Coco", "The Incredibles")));

        this.movies.put("teen", new ArrayList<>(
                Arrays.asList("The Avengers", "The Dark Knight", "Inception")));

        this.movies.put("adult", new ArrayList<>(
                Arrays.asList("The Godfather", "Deadpool", "Saving Private Ryan")));
    }
    @Test
    public void getRecommendedMovies_childUser_success() {
        BasicUser childUser = new BasicUser(11);
        BasicMovieRecommender basicMovieRecommender = new BasicMovieRecommender(childUser);
        List<String> movieRecommendationList = basicMovieRecommender.getRecommendedMovies();
        List<String> expectedMovieRecommendationList = this.movies.get("child");

        Assert.assertTrue(movieRecommendationList.containsAll(expectedMovieRecommendationList)
                && expectedMovieRecommendationList.containsAll(movieRecommendationList));
    }

    @Test
    public void getRecommendedMovies_teenUser_success() {
        BasicUser teenUser = new BasicUser(14);
        BasicMovieRecommender basicMovieRecommender = new BasicMovieRecommender(teenUser);
        List<String> movieRecommendationList = basicMovieRecommender.getRecommendedMovies();
        List<String> expectedMovieRecommendationList = this.movies.get("teen");

        Assert.assertTrue(movieRecommendationList.containsAll(expectedMovieRecommendationList)
                && expectedMovieRecommendationList.containsAll(movieRecommendationList));
    }

    @Test
    public void getRecommendedMovies_adultUser_success() {
        BasicUser adultUser = new BasicUser(19);
        BasicMovieRecommender basicMovieRecommender = new BasicMovieRecommender(adultUser);
        List<String> movieRecommendationList = basicMovieRecommender.getRecommendedMovies();
        List<String> expectedMovieRecommendationList = this.movies.get("adult");

        Assert.assertTrue(movieRecommendationList.containsAll(expectedMovieRecommendationList)
                && expectedMovieRecommendationList.containsAll(movieRecommendationList));
    }
}
