import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Silver on 2/23/2017.
 */
public class Maze {
    private int height,width;
    private Point startPoint,endPoint;
//todo change this tile collection to something more efficient later on, hashmap maybe?
    private ArrayList<MazeTile> mazeTiles;
    HashMap<Point, MazeTile> Map;

    public Maze(int[] dimension, ArrayList<MazeTile> mazeTiles, Point startPoints, Point endPoints) {
        this.height = dimension[1];
        this.width = dimension[0];
        this.mazeTiles = mazeTiles;
        this.startPoint = startPoints;
        this.endPoint = endPoints;
    }

    public Maze(int[] dimension, HashMap<Point, MazeTile> tileMap, Point startPoint, Point endPoint) {
        this.height = dimension[1];
        this.width = dimension[0];
        this.mazeTiles = mazeTiles;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.Map = tileMap;
    }

    public void play() {
        System.out.println("finished");
    }

    MazeTile getTile(Point reference){
        MazeTile result = new MazeTile(null,null);

        return result;
    }
}
