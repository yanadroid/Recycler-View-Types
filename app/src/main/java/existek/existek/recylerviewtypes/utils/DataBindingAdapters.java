package existek.existek.recylerviewtypes.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;


public class DataBindingAdapters {

    @BindingAdapter("bitmap")
    public static void setImageUri(ImageView view, String url) {
        UploadImageHelper helper = new UploadImageHelper();
        helper.uploadImage(url);
        helper.setDrawableCallback(view::setImageBitmap);
    }
}
