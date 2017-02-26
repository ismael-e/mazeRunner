import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Silver on 2/23/2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Maze {

    private Adventurer adventurer;
    private int height,width;
    private Point startPoint;
    private Point endPoint;
    private HashMap<Point, MazeTile> Map;

    public Maze(int[] dimension, HashMap<Point, MazeTile> tileMap, Point startPoint, Point endPoint) {
        setDimensions(dimension[0], dimension[1]);
        setStartPoint(startPoint);
        setEndPoint(endPoint);
        this.Map = tileMap;
        adventurer = new Adventurer(this);
        System.out.println("---Maze loaded---");
        System.out.println("Size :" + getWidth() + " BY " + getHeight() );
        System.out.println("Entrance X :" + getStartPoint().x + " Y :" +getStartPoint().y);
        System.out.println("Exit X :" + getEndPoint().x + " Y :" +getEndPoint().y);
    }

    public void solve() {
        //place the adventurer on the start tile and start his journey
        ArrayList<LogEntry> result = adventurer.startQuest(startPoint);
        if (result!=null){
            //process the log entries to generate a solution for the console.
            displaySolution(result);
        }
        else {
            //show a fail message
            failed();
        }
    }

    private void failed() {
        System.out.println("Maze could not be solved.");
    }

    private void displaySolution(ArrayList<LogEntry> result) {
        System.out.println("---PATH OUT---");
        markSolution(result);

        //use the dimensions of the maze as key to pull out the marked tiles from the map

        for (int y=0; y<getHeight(); y++){
            String solutionLine = new String();

            for (int x=0; x<getWidth(); x++){
//                System.out.println("sol : x:"+x+" y:"+y);
                MazeTile currentTile = getTile( new Point(x,y) );
                solutionLine = solutionLine + " " + currentTile.display();
            }
            System.out.println(solutionLine);
        }

    }

    private MazeTile getTile(Point reference){
        MazeTile result = Map.get(reference);
        return result;
    }

    public MazeTile[] getAdjacentTiles(Point location) {
        MazeTile[] result = new MazeTile[4];
        //check N S E W
        Point[] adjacentPoints = new Point[4];
        
        adjacentPoints[0] = new Point(location.x ,location.y+1);
        adjacentPoints[1] = new Point(location.x ,location.y-1);
        adjacentPoints[2] = new Point(location.x+1 ,location.y);
        adjacentPoints[3] = new Point(location.x-1 ,location.y);

        for(int i=0; i<adjacentPoints.length; i++){
            MazeTile tile = getTile(adjacentPoints[i]);
            if( tile!=null && tile.getType().equals("tunnel")){
                result[i]=tile;
            }
        }

        return result;
    }

    public void markTile(Point location) {
        //marks tile as has been visited
        MazeTile tile = Map.get(location);
        tile.setVisited();
        Map.put(location,tile);
    }

    public void markSolution(ArrayList<LogEntry> result) {
        Iterator<LogEntry> solution = result.iterator();

        while(solution.hasNext()) {
            LogEntry currentEntry = solution.next();
            Point location = currentEntry.getLocation();
            //marks tile as part of the solution
            MazeTile tile = Map.get(location);
            tile.setType("solution");
            Map.put(location,tile);
        }
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public Point getEndPoint() {
        return endPoint;
    }
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    public void setDimensions(int width, int height) {
        this.height = height;
        this.width = width;
    }
    public Point getStartPoint() {
        return startPoint;
    }
}
