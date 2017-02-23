/**
 * Created by Silver on 2/23/2017.
 */
public class MazeTile {
    private int[][] position;
    private String type;

    public MazeTile(int[][] position, String type) {
        this.position = position;
        this.type = type;
    }

    public int[][] getPosition() {
        return position;
    }

    public void setPosition(int[][] position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
