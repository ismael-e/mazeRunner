import com.sun.xml.internal.bind.v2.TODO;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by Silver on 2/23/2017.
 */
public class MazeImporter {

    private String[] mazeArray;
    List<String> mazeArrayList = new ArrayList<String>();
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
        int startX = stringScanner.nextInt();
        int startY = stringScanner.nextInt();
        Point startPoint = new Point(startX,startY);

        stringScanner = new Scanner(endLine);
        int endX = stringScanner.nextInt();
        int endY = stringScanner.nextInt();
        Point endPoint = new Point(endX,endY);

        //skipping first three lines of the text file to
        Iterator mazeIterator = mazeArrayList.listIterator(3);

        ArrayList<MazeTile> mazeTiles = new ArrayList<MazeTile>();

        HashMap<Point,MazeTile> tileMap = new HashMap<Point, MazeTile>();

        int lineIndex = 0;
        while(mazeIterator.hasNext()){
            String currentString = (String) mazeIterator.next();
            lineIndex++;

            char[] charArray = currentString.replaceAll(" ","").toCharArray();

            for(int i=0; i < charArray.length ;i++){
                char currentCharacter = charArray[i];

                Point tilePosition = new Point(lineIndex,i);
                String tileType;

                if(currentCharacter =='1'){
                    //Todo change the wall and tunnel to constants when i get some time
                    tileType = "wall";
                }
                else{
                    tileType = "tunnel";
                }

                MazeTile tile = new MazeTile(tilePosition,tileType);
                tileMap.put(tilePosition,tile);
                mazeTiles.add(tile);
            }
        }
//        maze = new Maze(dimensions,mazeTiles,startPoint,endPoint);
        maze = new Maze(dimensions,tileMap,startPoint,endPoint);

    }

    public Maze getMaze() {
        return maze;
    }
}
