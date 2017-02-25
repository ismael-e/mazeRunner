import java.awt.*;
import java.util.HashMap;

/**
 * Created by Silver on 2/23/2017.
 */
public class Maze {

    Adventurer adventurer;
    private int height,width;
    private Point startPoint;
    private Point endPoint;
//todo change this tile collection to something more efficient later on, hashmap maybe?
//    private ArrayList<MazeTile> mazeTiles;
    HashMap<Point, MazeTile> Map;

//    public Maze(int[] dimension, ArrayList<MazeTile> mazeTiles, Point startPoints, Point endPoints) {
//        this.height = dimension[1];
//        this.width = dimension[0];
//        this.mazeTiles = mazeTiles;
//        this.startPoint = startPoints;
//        this.endPoint = endPoints;
//    }

    public Maze(int[] dimension, HashMap<Point, MazeTile> tileMap, Point startPoint, Point endPoint) {
        this.height = dimension[1];
        this.width = dimension[0];
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.Map = tileMap;
        adventurer = new Adventurer(this);
    }

    public void solve() {
        //place the adventurer on the start tile and start his journey
        adventurer.startQuest(startPoint);

    }

    private MazeTile getTile(Point reference){
        MazeTile result = Map.get(reference);
        return result;
    }

    public MazeTile[] getAdjacentTiles(Point location) {
        MazeTile[] result = new MazeTile[4];
        //check N S E W
        Point[] adjacentPoints = new Point[4];
        
        adjacentPoints[1] = new Point(location.x ,location.y+1);
        adjacentPoints[2] = new Point(location.x ,location.y-1);
        adjacentPoints[3] = new Point(location.x+1 ,location.y);
        adjacentPoints[4] = new Point(location.x-1 ,location.y);

        for(int i=0; i<adjacentPoints.length; i++){
            MazeTile tile = Map.get(adjacentPoints[i]);
            if(tile.getType().equals("0")){
                result[i]=tile;
            }
        }

        return result;
    }

    public void markTile(Point location) {
        //marks tile as has been visited
        MazeTile tile = Map.get(location);
        tile.setVisited(true);
        Map.put(location,tile);
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
