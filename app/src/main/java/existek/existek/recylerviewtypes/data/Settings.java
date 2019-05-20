package existek.existek.recylerviewtypes.data;

public class Settings implements Type {

    private String text;

    public Settings(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public int getType() {
        return Type.TYPE_SETTINGS;
    }
}
