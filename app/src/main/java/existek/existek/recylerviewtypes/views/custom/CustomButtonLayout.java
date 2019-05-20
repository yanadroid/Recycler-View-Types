package existek.existek.recylerviewtypes.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import existek.existek.recylerviewtypes.R;

public class CustomButtonLayout extends LinearLayout implements View.OnClickListener {

    private int max = 2;
    private int adapterPosition;
    private int currentId = -1;
    private boolean isClicked = false;
    private boolean isExpandable = false;
    private View[] views;
    private OnPositionCallback callback;

    public interface OnPositionCallback {
        void onPosition(int id, int position);
        void expandableOpen();
        void expandableClose();
    }

    public CustomButtonLayout(Context context) {
        super(context);
    }

    public CustomButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCallback(OnPositionCallback callback) {
        this.callback = callback;
    }


    private void init() {
        views = new View[max];
        for (int i = 0; i < max; i++) {
            View v = createButton(i);
            if (v.getParent() != null) {
                ((ViewGroup) v.getParent()).removeView(v);
            }
            addView(v);
            views[i] = v;
        }
    }

    private Button createButton(int i) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            Button button = (Button) inflater.inflate(R.layout.view_custom_button, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            button.setOnClickListener(this);
            button.setId(adapterPosition + (i+1));
            button.setText("Item " + i);
            addView(button, params);
            return button;
    }


    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public void setCurrentView(int id) {
        currentId = id;
        findViewById(id).setBackgroundResource(R.drawable.background_button_selected);
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public void setMax(int max, int position) {
        this.max = max;
        this.adapterPosition = position;
        init();
        invalidate();
        requestLayout();
    }

    @Override
    public void onClick(View v) {
        if (!isExpandable) {
            callback.expandableOpen();
            isExpandable = true;
        }

        for (int i = 0; i < max; i++) {
            if (v.getId() != views[i].getId()) {
                findViewById(views[i].getId()).setBackgroundResource(R.drawable.background_button_normal);
                findViewById(views[i].getId()).setTag(null);
            } else {
                if (!isClicked) {
                    currentId = v.getId();
                    findViewById(v.getId()).setBackgroundResource(R.drawable.background_button_selected);
                    isClicked = true;
                    callback.onPosition(currentId, i);
                } else {
                    if (v.getId() == currentId) {
                        findViewById(v.getId()).setBackgroundResource(R.drawable.background_button_normal);
                        isClicked = false;
                        callback.expandableClose();
                        isExpandable = false;
                        findViewById(v.getId()).setTag(null);
                    } else {
                        currentId = v.getId();
                        findViewById(v.getId()).setBackgroundResource(R.drawable.background_button_selected);
                        isClicked = true;
                        callback.onPosition(currentId, i);
                    }
                }
            }
        }
    }
}
