import java.awt.*;

/**
 * Created by Silver on 2/23/2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MazeTile {
    private final Point position;
    private String type;
    private boolean visited = false;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    public MazeTile(Point position, String type) {
        this.position = position;
        this.type = type;
    }

    public Point getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String display() {
        String result = null;
        String type = getType();
        switch (type) {
            case "wall":
                result = "#";
                break;
            case "tunnel":
                result = " ";
                break;
            case "solution":
                result = "x";
                break;
        }
        return result;
    }
}
