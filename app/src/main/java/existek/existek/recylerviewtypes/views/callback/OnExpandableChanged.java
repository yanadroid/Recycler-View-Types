package existek.existek.recylerviewtypes.views.callback;

public interface OnExpandableChanged extends BaseCallback {
    void notify(int id, int pos, int current, boolean expandable);
}
