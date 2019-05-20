package existek.existek.recylerviewtypes.views.callback;

public interface OnNotifyProgressCallback extends BaseCallback {
    void notify(int progress, int position, boolean stop, boolean start);
}
