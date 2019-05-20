package existek.existek.recylerviewtypes.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.net.URL;

import existek.existek.recylerviewtypes.views.callback.OnUploadImageCallback;

public class UploadImageHelper {

    private Handler handler;
    private OnUploadImageCallback callback;

    public void uploadImage(String url) {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.obj != null && msg.obj instanceof Bitmap) {
                    Bitmap bitmap = compressBitmap((Bitmap)msg.obj);
                    callback.setImage(bitmap);
                    if (handler != null) {
                        handler = null;
                    }
                }
            };
        };
        new Thread() {
            public void run() {
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
                    Message msg = new Message();
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void setDrawableCallback(OnUploadImageCallback callback) {
        this.callback = callback;
    }

    private Bitmap compressBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,30, stream);
        byte[] byteArray = stream.toByteArray();
        return BitmapFactory.decodeByteArray(byteArray,0, byteArray.length);
    }
}
