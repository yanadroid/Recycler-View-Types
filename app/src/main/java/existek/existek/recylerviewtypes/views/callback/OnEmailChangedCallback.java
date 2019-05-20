package existek.existek.recylerviewtypes.views.callback;

public interface OnEmailChangedCallback extends BaseCallback {
    void notify(int pos, String text, boolean error, boolean typing, boolean normal);
}
