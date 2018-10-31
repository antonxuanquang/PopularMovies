package utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmovies.R;

public class ImageUtils {

    public static void loadImageTo(ImageView imageView, String path) {
        Picasso.get()
                .load(StringUtils.API_IMAGE_BASE_URL + path)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(imageView);
    }
}
