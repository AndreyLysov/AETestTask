package tests;

import data_types.Tweet;
import helpers.App;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterTests {

    App app;

    @Before
    public void setUp() {
        app = new App();
    }

    @Test
    public void createTweet() {
        Tweet tweet = new Tweet();
        Assert.assertTrue(String.format("%s isn't added", tweet.toString()),
                tweet
                        .equals(new Tweet(
                                        app.api.getTweet(
                                                app.api.update(tweet).getBody().jsonPath().getInt("id"))
                                )
                        ));
    }

    @Test
    public void deleteTweet() {
        Tweet tweet = new Tweet();
        Assert.assertTrue(String.format("%s is not deleted", tweet.toString()),
                tweet
                        .equals(new Tweet(
                                        app.api.deleteTweet(
                                                app.api.update(tweet).getBody().jsonPath().getInt("id"))
                                )
                        ));
    }
}
