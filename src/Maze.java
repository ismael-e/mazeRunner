/**
 * Created by Silver on 2/23/2017.
 */
public class Maze {
    private int height,width;
    private MazeTile[][] mazeArray;
    private MazeTile entranceTile,exitTile;
    private int[] startPoint;
    private int[] endPoint;

    public int[] getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int[] startPoint) {
        this.startPoint = startPoint;
    }

    public int[] getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int[] endPoint) {
        this.endPoint = endPoint;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public MazeTile[][] getMazeArray() {
        return mazeArray;
    }

    public void setMazeArray(MazeTile[][] mazeArray) {
        this.mazeArray = mazeArray;
    }

    public MazeTile getEntranceTile() {
        return entranceTile;
    }

    public void setEntranceTile(MazeTile entranceTile) {
        this.entranceTile = entranceTile;
    }

    public MazeTile getExitTile() {
        return exitTile;
    }

    public void setExitTile(MazeTile exitTile) {
        this.exitTile = exitTile;
    }


    public void play() {
        System.out.println("finished");
    }

    public void addTile(MazeTile tile) {
    }
}
