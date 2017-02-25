import java.awt.*;

/**
 * Created by Silver on 2/23/2017.
 */
public class MazeTile {
    private Point position;
    private String type;
    boolean visited = false;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public MazeTile(Point position, String type) {
        this.position = position;
        this.type = type;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
