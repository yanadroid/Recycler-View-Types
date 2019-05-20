package existek.existek.recylerviewtypes.views.callback;

import android.support.design.widget.AppBarLayout;

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State state = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (state != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            state = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (state != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            state = State.COLLAPSED;
        } else {
            if (state != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            state = State.IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
