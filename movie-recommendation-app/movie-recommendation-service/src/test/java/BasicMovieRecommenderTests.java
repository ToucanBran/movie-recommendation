
import common.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicMovieRecommenderTests {
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
        User childUser = mock(User.class);
        when(childUser.getAge()).thenReturn(11);
        BasicMovieRecommender basicMovieRecommender = new BasicMovieRecommender(childUser);
        List<String> movieRecommendationList = basicMovieRecommender.getRecommendedMovies();
        List<String> expectedMovieRecommendationList = this.movies.get("child");

        Assert.assertTrue(movieRecommendationList.containsAll(expectedMovieRecommendationList)
                            && expectedMovieRecommendationList.containsAll(movieRecommendationList));
    }

    @Test
    public void getRecommendedMovies_teenUser_success() {
        User teenUser = mock(User.class);
        when(teenUser.getAge()).thenReturn(15);
        BasicMovieRecommender basicMovieRecommender = new BasicMovieRecommender(teenUser);
        List<String> movieRecommendationList = basicMovieRecommender.getRecommendedMovies();
        List<String> expectedMovieRecommendationList = this.movies.get("teen");

        Assert.assertTrue(movieRecommendationList.containsAll(expectedMovieRecommendationList)
                && expectedMovieRecommendationList.containsAll(movieRecommendationList));
    }

    @Test
    public void getRecommendedMovies_adultUser_success() {
        User adultUser = mock(User.class);
        when(adultUser.getAge()).thenReturn(21);
        BasicMovieRecommender basicMovieRecommender = new BasicMovieRecommender(adultUser);
        List<String> movieRecommendationList = basicMovieRecommender.getRecommendedMovies();
        List<String> expectedMovieRecommendationList = this.movies.get("adult");

        Assert.assertTrue(movieRecommendationList.containsAll(expectedMovieRecommendationList)
                && expectedMovieRecommendationList.containsAll(movieRecommendationList));
    }

    @Test
    public void getRecommendedMovies_userException_success() {
        User user = mock(User.class);
        when(user.getAge()).thenThrow(new RuntimeException());
        BasicMovieRecommender basicMovieRecommender = new BasicMovieRecommender(user);
        List<String> movieRecommendationList = basicMovieRecommender.getRecommendedMovies();
        List<String> expectedMovieRecommendationList = this.movies.get("child");

        Assert.assertTrue(movieRecommendationList.containsAll(expectedMovieRecommendationList)
                && expectedMovieRecommendationList.containsAll(movieRecommendationList));
    }

    @Test
    public void getRecommendedMovies_userTimeout_success() {
        User user = mock(User.class);
        when(user.getAge()).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws InterruptedException {
                Thread.sleep(101);
                return 21;
            }
        });
        BasicMovieRecommender basicMovieRecommender = new BasicMovieRecommender(user);
        List<String> movieRecommendationList = basicMovieRecommender.getRecommendedMovies();
        List<String> expectedMovieRecommendationList = this.movies.get("child");

        Assert.assertTrue(movieRecommendationList.containsAll(expectedMovieRecommendationList)
                && expectedMovieRecommendationList.containsAll(movieRecommendationList));
    }
}