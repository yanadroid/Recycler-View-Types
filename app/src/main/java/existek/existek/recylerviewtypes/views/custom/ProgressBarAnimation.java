package existek.existek.recylerviewtypes.views.custom;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class ProgressBarAnimation extends Animation {

    private OnProgressListener listener;
    private ProgressBar progressBar;
    private float from;
    private float to;

    public ProgressBarAnimation(ProgressBar progressBar) {
        super();
        this.progressBar = progressBar;
    }

    public void start(float from, float to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        listener.progress((int) value);
    }


    public interface OnProgressListener {
        void progress(int progress);
    }

    public void setListener(OnProgressListener listener) {
        this.listener = listener;
    }
}