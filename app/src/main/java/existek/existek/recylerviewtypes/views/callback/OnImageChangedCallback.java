package existek.existek.recylerviewtypes.views.callback;

import android.graphics.Bitmap;

public interface OnImageChangedCallback extends BaseCallback {
    void notify(Bitmap bitmap, int position);
}
