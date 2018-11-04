package utils;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.udacity.android.popularmovies.data.MovieTrailer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static URL buildMovieListURL(String userReference, int page) {
        String basedUrl = StringUtils.API_BASE_URL + userReference;
        Uri buildUri = Uri.parse(basedUrl).buildUpon()
                .appendQueryParameter(StringUtils.API_KEY_STRING, StringUtils.API_KEY)
                .appendQueryParameter(StringUtils.PAGE, String.valueOf(page))
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildMovieTrailersURL(Integer movieId) {
        // http://api.themoviedb.org/3/movie/346910/videos?api_key=[API_KEY]
        return buildURLWithFormatAPI("%s%d/videos", movieId);
    }

    public static URL buildMovieReviewsURL(Integer movieId) {
        // http://api.themoviedb.org/3/reviews/346910/videos?api_key=[API_KEY]
        return buildURLWithFormatAPI("%s%d/reviews", movieId);
    }

    @Nullable
    private static URL buildURLWithFormatAPI(String urlFormat, Integer movieId2) {
        String baseURL = String.format(urlFormat, StringUtils.API_BASE_URL, movieId2);
        Uri buildUri = Uri.parse(baseURL).buildUpon()
                .appendQueryParameter(StringUtils.API_KEY_STRING, StringUtils.API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromURL(URL api) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) api.openConnection();
        try {
            InputStream in = connection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            connection.disconnect();
        }
    }


    public static Uri buildYoutubeTrailerUri(MovieTrailer trailer) {
        String basedUrl = StringUtils.YOUTUBE_BASE_URL;
        return Uri.parse(basedUrl).buildUpon()
                .appendQueryParameter(StringUtils.YOUTUBE_V_PARAMS, trailer.getKey())
                .build();
    }

    public static Uri buildReviewUri(String reviewUrl) {
        return Uri.parse(reviewUrl).buildUpon()
                .build();
    }
}
