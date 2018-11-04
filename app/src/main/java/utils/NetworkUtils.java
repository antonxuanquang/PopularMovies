package utils;

import android.net.Uri;

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
        // http://api.themoviedb.org/3/movie/346910/videos?api_key=7f6a5f962527c4d8a72d9d300db2ba2a
        String baseURL = String.format("%s/%d/videos", StringUtils.API_BASE_URL, movieId);
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


}
