package existek.existek.recylerviewtypes.data;

import java.util.LinkedList;
import java.util.List;

public class Random implements Type {

    private int size;
    private int id = -1;
    private int currentPos;
    private boolean isExpandable;
    private List<RandomDescription> descriptions = new LinkedList<>();

    public Random(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            RandomDescription description = new RandomDescription("Descriptions: " + i);
            descriptions.add(description);
        }
    }

    public int getId() {
        return id;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    public int getSize() {
        return size;
    }


    public List<RandomDescription> getDescriptions() {
        return descriptions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    @Override
    public int getType() {
        return Type.TYPE_RANDOM;
    }
}
