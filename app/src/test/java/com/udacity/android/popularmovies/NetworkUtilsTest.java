package com.udacity.android.popularmovies;

import org.junit.Ignore;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import utils.NetworkUtils;
import utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NetworkUtilsTest {

    private final static String TOP_RATED = StringUtils.TOP_RATED;
    private final static String MOST_POPULAR_URL = "http://api.themoviedb.org/3/movie/top_rated?api_key=7f6a5f962527c4d8a72d9d300db2ba2a";


    @Test
    @Ignore
    public void givenMostPopularReference_buildURL_shouldReturnCorrectURL() throws MalformedURLException {
        String userReference = StringUtils.MOST_POPULAR;

        URL url = NetworkUtils.buildMovieListURL(userReference, 0);

        assertThat(url).isEqualTo(new URL(MOST_POPULAR_URL));
    }
}