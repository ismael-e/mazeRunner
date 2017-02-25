import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args)  {
        System.out.println("Hello World! Preparing to read input.txt");

        try {
            MazeImporter mazeImporter = new MazeImporter("input.txt");
            Maze maze = mazeImporter.getMaze();
            maze.solve();

        } catch (FileNotFoundException e) {
            System.out.println("IO ERROR > Something went wrong when trying to read the maze file, check the path and file name");
        }

    }
}
