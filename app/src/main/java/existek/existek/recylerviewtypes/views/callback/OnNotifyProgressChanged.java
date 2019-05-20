package existek.existek.recylerviewtypes.views.callback;

public interface OnNotifyProgressChanged extends BaseCallback {
    void notify(int progress, int position, boolean stop, boolean start);
}
