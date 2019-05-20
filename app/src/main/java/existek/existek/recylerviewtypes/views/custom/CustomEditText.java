package existek.existek.recylerviewtypes.views.custom;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.EditText;

import existek.existek.recylerviewtypes.R;
import existek.existek.recylerviewtypes.utils.ValidUtility;

public class CustomEditText extends EditText {

    private OnClickEventListener listener;
    private String text;
    private Drawable drawable;


    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence t, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        this.text = t.toString().trim();
        changeTint(t.length() != 0 ? R.color.colorGreen : R.color.colorGrey);
        showCloseIcon(!text.isEmpty());

        if (listener != null && t.length() != 0) {
            listener.onEventTyping();
        }

        if (t.length() == 0 && listener != null) {
            listener.onEventClose();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                validate();
                return true;

        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            listener.onEventTyping();
            changeTint(R.color.colorGreen);
        }
    }

    public void setListener(OnClickEventListener listener) {
        this.listener = listener;
    }

    private void validate() {
        if (ValidUtility.isValidEmail(text)) {
            changeTint(R.color.colorGrey);
            listener.onEnterEvent(text);
        } else {
            changeTint(R.color.colorAccent);
            listener.onEventError();
        }
    }

    public void changeTint(int color) {
        getBackground().mutate().setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
    }

    public void showCloseIcon(boolean show) {
        setCompoundDrawablesWithIntrinsicBounds(null, null, show ? ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp) : null, null);
    }

    public String getEmail() {
        return text;
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds( Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (right != null) {
            drawable = right;
        }
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && drawable != null) {
            Rect bounds = drawable.getBounds();
            final int x = (int) event.getX();
            final int y = (int) event.getY();
            if (x >= (this.getRight() - bounds.width()) && x <= (this.getRight() - this.getPaddingRight())
                    && y >= this.getPaddingTop() && y <= (this.getHeight() - this.getPaddingBottom())) {
                showCloseIcon(false);
                setText(null);
                listener.onEventClose();
                event.setAction(MotionEvent.ACTION_CANCEL);
            }
        }
        return super.onTouchEvent(event);
    }

    
    public interface OnClickEventListener {
        void onEventClose();

        void onEnterEvent(String value);

        void onEventError();

        void onEventTyping();
    }
}
