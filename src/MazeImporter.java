import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Silver on 2/23/2017.
 */
public class MazeImporter {

    private String[] mazeArray;
    private int mazeWidth;
    private int mazeHeight;




    public MazeImporter(String fileName) throws FileNotFoundException {


        File mazeFile = new File("src/"+ fileName);
        BufferedReader lineReader = new BufferedReader(new FileReader(mazeFile));


    }

    public String[] getMazeArray() {
        return mazeArray;
    }

    public void setMazeArray(String[] mazeArray) {
        this.mazeArray = mazeArray;
    }

    public int getMazeWidth() {
        return mazeWidth;
    }

    public void setMazeWidth(int mazeWidth) {
        this.mazeWidth = mazeWidth;
    }

    public int getMazeHeight() {
        return mazeHeight;
    }

    public void setMazeHeight(int mazeHeight) {
        this.mazeHeight = mazeHeight;
    }
}
