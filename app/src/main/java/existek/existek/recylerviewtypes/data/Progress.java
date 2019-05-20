package existek.existek.recylerviewtypes.data;

public class Progress implements Type {

    private int id;
    private int progress;
    private boolean isStarted;
    private boolean isStoped;


    public Progress(int id) {
        this.id = id;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isStoped() {
        return isStoped;
    }

    public void setStoped(boolean stoped) {
        isStoped = stoped;
    }

    @Override
    public int getType() {
        return Type.TYPE_PROGRESS;
    }
}
