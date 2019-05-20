package existek.existek.recylerviewtypes.views.callback;

public interface OnExpandableChangedCallback extends BaseCallback {
    void notify(int id, int pos, int current, boolean expandable);
}
