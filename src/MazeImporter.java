import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by Silver on 2/23/2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MazeImporter {

    public static final int SizeLine = 0;
    public static final int StartLine = 1;
    public static final int EndLine = 2;
    public static final int MazeBodyLine = 3;
    private List<String> mazeArrayList = new ArrayList<String>();
    private Maze maze;

    public MazeImporter(String fileName) throws FileNotFoundException {

        //load text file into memory as an ArrayList
        loadTextFile(fileName);

        //loading up maze parameters to create a maze object
        int[] dimensions = getMazeDimensions(mazeArrayList.get(SizeLine));
        Point startPoint = getCoordinatePoint(mazeArrayList.get(StartLine));
        Point endPoint = getCoordinatePoint(mazeArrayList.get(EndLine));

        //process the maze body into a map
        HashMap<Point, MazeTile> mazeMap = mapMazeBody();

        maze = new Maze(dimensions,mazeMap,startPoint,endPoint);
    }

    private int[] getMazeDimensions(String sizeLine) {
        Scanner stringScanner = new Scanner(sizeLine);
        int[] dimensions = new int[2];
        dimensions[0] = stringScanner.nextInt();
        dimensions[1] = stringScanner.nextInt();

        return dimensions;
    }

    private Point getCoordinatePoint(String endLine) {
        Scanner stringScanner;
        stringScanner = new Scanner(endLine);
        int endX = stringScanner.nextInt();
        int endY = stringScanner.nextInt();
        return new Point(endX,endY);
    }

    private HashMap<Point, MazeTile> mapMazeBody() {
        //skipping first three lines of the text file
        Iterator mazeIterator = mazeArrayList.listIterator(MazeBodyLine);

        HashMap<Point,MazeTile> tileMap = new HashMap<Point, MazeTile>();
        int lineIndex = 0;
        while(mazeIterator.hasNext()){
            String currentString = (String) mazeIterator.next();

            char[] charArray = currentString.replaceAll(" ","").toCharArray();

            for(int i=0; i < charArray.length ;i++){
                char currentCharacter = charArray[i];

                Point tilePosition = new Point(i,lineIndex);
                String tileType;

                if(currentCharacter == '1'){
                    //Todo change the wall and tunnel to constants when i get some time
                    tileType = "wall";
                }
                else{
                    tileType = "tunnel";
                }

                MazeTile tile = new MazeTile(tilePosition,tileType);
                tileMap.put(tilePosition,tile);
            }
            lineIndex++;
        }
        return tileMap;
    }

    private void loadTextFile(String fileName) throws FileNotFoundException {
        File mazeFile = new File("src/mazeFiles/"+ fileName);
        BufferedReader lineReader = new BufferedReader(new FileReader(mazeFile));
        String readLine;

        //loading maze file from .txt to an array list
        try {
            while ((readLine = lineReader.readLine()) != null) {
                mazeArrayList.add(readLine);
            }
        } catch (IOException e) {
            System.out.println("File could not be located, check path and filename");
        }
    }

    public Maze getMaze() {
        return maze;
    }
}
