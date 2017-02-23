import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Silver on 2/23/2017.
 */
public class MazeImporter {

    private String[] mazeArray;
    List<String> mazeArrayList = new ArrayList<String>();
    int conditionPoints[] = new int[2];

    public MazeImporter(String fileName) throws FileNotFoundException {

        Maze maze = new Maze();
        File mazeFile = new File("src/mazeFiles/"+ fileName);
        BufferedReader lineReader = new BufferedReader(new FileReader(mazeFile));
        String readLine;

        //loading maze file from .txt to an array list
        try {
            while ((readLine = lineReader.readLine()) != null) {
                System.out.println(readLine);
                mazeArrayList.add(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //loading up maze parameters to create a maze object
        String sizeLine = mazeArrayList.get(0);
        String startLine = mazeArrayList.get(1);
        String endLine = mazeArrayList.get(2);

        Scanner stringScanner = new Scanner(sizeLine);
        maze.setWidth(stringScanner.nextInt());
        maze.setHeight(stringScanner.nextInt());

        stringScanner = new Scanner(startLine);

        conditionPoints[0] = stringScanner.nextInt();
        conditionPoints[1] = stringScanner.nextInt();
        maze.setStartPoint(conditionPoints);

        stringScanner = new Scanner(endLine);
        conditionPoints[0] = stringScanner.nextInt();
        conditionPoints[1] = stringScanner.nextInt();
        maze.setStartPoint(conditionPoints);

        Iterator mazeIterator = mazeArrayList.listIterator(3);
        while(mazeIterator.hasNext()){
            String currentString = (String) mazeIterator.next();


        }
    }

    public String[] getMazeArray() {
        return mazeArray;
    }

    public void setMazeArray(String[] mazeArray) {
        this.mazeArray = mazeArray;
    }

}
