import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by Silver on 2/27/2017.
 */
public class EndResultTest {
    private static final String TEST_RESOURCES = "tests/testResources/";
    private static final String SUCCESS_MESSAGE = "---PATH OUT---";
    private static final String FAIL_MESSAGE = "Maze could not be solved.";
    ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
     MazeImporter mazeImporter;
    Maze maze;

    @Before
    public  void setup() throws FileNotFoundException {
        System.setOut(new PrintStream(consoleOutput));
    }

    @After
    public  void clearConsole() {
        System.setOut(null);
    }

    @Test(timeout=30000)
    public void testBasicSolvableSmallMaze() throws FileNotFoundException {

        mazeImporter = new MazeImporter(TEST_RESOURCES + "small.txt");
        maze = mazeImporter.getMaze();
        maze.solve();
        Assert.assertThat(consoleOutput.toString() , containsString(SUCCESS_MESSAGE));
    }

    @Test(timeout=30000)
    public void testBasicSolvableHardMaze() throws FileNotFoundException {

        mazeImporter = new MazeImporter(TEST_RESOURCES + "large_input.txt");
        maze = mazeImporter.getMaze();
        maze.solve();
        Assert.assertThat(consoleOutput.toString() , containsString(SUCCESS_MESSAGE));
    }

    @Test(timeout=30000)
    public void testLoopedSolvableSmallMaze() throws FileNotFoundException {

        mazeImporter = new MazeImporter(TEST_RESOURCES + "looped_small.txt");
        maze = mazeImporter.getMaze();
        maze.solve();
        Assert.assertThat(consoleOutput.toString() , containsString(SUCCESS_MESSAGE));
    }

    @Test(timeout=30000)
    public void testBlockedSolvableMaze() throws FileNotFoundException {

        mazeImporter = new MazeImporter(TEST_RESOURCES + "blocked_medium_input.txt");
        maze = mazeImporter.getMaze();
        maze.solve();
        Assert.assertThat(consoleOutput.toString() , containsString(FAIL_MESSAGE));
    }

    @Test(timeout=30000)
    public void testBasicOpenMaze() throws FileNotFoundException {

        mazeImporter = new MazeImporter(TEST_RESOURCES + "sparse_medium.txt");
        maze = mazeImporter.getMaze();
        maze.solve();
        Assert.assertThat(consoleOutput.toString() , containsString(SUCCESS_MESSAGE));
    }
}
