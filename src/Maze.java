import java.util.ArrayList;

/**
 * Created by Silver on 2/23/2017.
 */
public class Maze {
    private int height,width;
    private MazeTile[][] mazeArray;

    private ArrayList<MazeTile> mazeTiles;

    public Maze(int[] dimension, ArrayList<MazeTile> mazeTiles, int[] startPoints, int[] endPoints) {
        this.height = dimension[1];
        this.width = dimension[0];
        this.mazeTiles = mazeTiles;

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


    public void play() {
        System.out.println("finished");
    }

    public void addTile(MazeTile tile) {
    }

    public void setTiles(ArrayList<MazeTile> mazeTiles) {
        this.mazeTiles = mazeTiles;
    }
}
