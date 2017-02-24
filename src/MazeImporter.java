import com.sun.xml.internal.bind.v2.TODO;

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
    int startPoints[] = new int[2];
    int endPoints[] = new int[2];
    int dimensions[] = new int[2];
    Maze maze;

    public MazeImporter(String fileName) throws FileNotFoundException {


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
        dimensions[0] = stringScanner.nextInt();
        dimensions[1] = stringScanner.nextInt();

        stringScanner = new Scanner(startLine);
        startPoints[0] = stringScanner.nextInt();
        startPoints[1] = stringScanner.nextInt();

        stringScanner = new Scanner(endLine);
        endPoints[0] = stringScanner.nextInt();
        endPoints[1] = stringScanner.nextInt();


        Iterator mazeIterator = mazeArrayList.listIterator(3);

        ArrayList<MazeTile> mazeTiles = new ArrayList<MazeTile>();

        int lineIndex = 0;
        while(mazeIterator.hasNext()){
            String currentString = (String) mazeIterator.next();
            lineIndex++;

            char[] charArray = currentString.replaceAll(" ","").toCharArray();

            for(int i=0; i < charArray.length ;i++){
                char currentCharacter = charArray[i];

                int[][] tilePosition = new int[lineIndex][i];
                String tileType;

                if(currentCharacter =='1'){
                    //Todo change the wall and tunnel to constants when i get some time
                    tileType = "wall";
                }
                else{
                    tileType = "tunnel";
                }

                MazeTile tile = new MazeTile(tilePosition,tileType);

                mazeTiles.add(tile);
            }
        }
        maze = new Maze(dimensions,mazeTiles,startPoints,endPoints);

    }

    public String[] getMazeArray() {
        return mazeArray;
    }

    public void setMazeArray(String[] mazeArray) {
        this.mazeArray = mazeArray;
    }

    public Maze getMaze() {

        return maze;
    }
}
