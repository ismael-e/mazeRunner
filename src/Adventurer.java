import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by silver on 25/02/2017.
 */
@SuppressWarnings("ALL")
public class Adventurer {
    private final Maze maze;
    ArrayList<LogEntry> travelLog;
    private int moveCounter = 0;

    public Adventurer(Maze maze) {
        this.maze = maze;
        travelLog = new ArrayList<LogEntry>();
    }

    public ArrayList<LogEntry> startQuest(Point location) {
        return moveTo(location);
    }

    private ArrayList<LogEntry> moveTo(Point nextLocation) {
        System.out.println("---Adventurer makes a move---");
        System.out.println("Moved to X :" + nextLocation.x + " Y :" + nextLocation.y);
        Point location = nextLocation;
        moveCounter++;
        boolean fork = false;

        //mark the current tile as visited
        maze.markTile(location);

        //check if this is a win
        if(location.equals(maze.getEndPoint())){

            System.out.println("---SALVATION---");
            //adventure has completed his quest and returns his travel log
            logMove(moveCounter, location, fork);
            return endQuest();
        }

        //get all possible valid moves for current location
        MazeTile[] moveOptions = getMoveOptions(location);

        int moveCouner = countValidMoves(moveOptions);

        //no moves available means the current tile is a dead end.
        if(moveCouner == 0){
            //get the coordinates of the last fall back location to retry
            Point fallBackPoint = getFallBack();
            return attemptFallBack(fallBackPoint);
        }
        //if there is more than one move option then this tile is a fork
        else if(moveCouner > 1){
            fork = true;
        }

        //keep track of each move to show final solution and note down any forks in the tunnel to fall back to in event of a dead-end
        logMove(moveCounter, location, fork);

        //choose a move from the move counter
        Point nextMove = chooseMove(moveOptions);

        //play again
        return moveTo(nextMove);

    }

    private ArrayList<LogEntry> attemptFallBack(Point fallBackPoint) {
        if(fallBackPoint != null){
            System.out.println("---Adventurer is stuck!---");
            System.out.println("Falling back to X :" + fallBackPoint.x + " Y :" + fallBackPoint.y);
            return moveTo(fallBackPoint);
        }
        else{
            System.out.println("The Adventurer has nowhere left to go..... RIP");
            return null;
        }
    }

    private int countValidMoves(MazeTile[] moveOptions) {
        int moveCouner = 0;
        for (int i=0; i<moveOptions.length; i++){
            if (moveOptions[i]!=null){
                moveCouner++;
            }
        }
        return moveCouner;
    }

    private Point getFallBack() {
        //preparing to iterate backwards to get last fall back point
        ListIterator<LogEntry> entries = travelLog.listIterator(travelLog.size());
        while (entries.hasPrevious()){
            LogEntry currentEntry = entries.previous();
            int entryIndex = travelLog.indexOf(currentEntry);
            if(currentEntry.isFork()){
                return currentEntry.getLocation();
            }
            else{
                //removing all steps to last fall back point
                entries.remove();
            }
        }
        return null;
    }

    private ArrayList<LogEntry> endQuest() {
        System.out.println("Exit located");
        return travelLog;
    }

    private Point chooseMove(MazeTile[] moveOptions) {
        // dumb selection , choosing the first available valid move or return a null
        Point result = null;
        for (int i=0; i<moveOptions.length; i++){
            if(moveOptions[i] != null ){
                result  = moveOptions[i].getPosition();
                return result;
            }
        }
        return result;
    }

    private void logMove(int moveCounter, Point location, boolean fork) {
        //log the current move count , location and if it was a fork
        LogEntry currentEntry = new LogEntry(moveCounter,location,fork);
        travelLog.add(currentEntry);
    }

    private MazeTile[] getMoveOptions(Point location) {
        //get list of free tiles adjacent to the current tile
        MazeTile[] freeTiles = maze.getAdjacentTiles(location);

        for(int i=0; i<freeTiles.length; i++){
            if( freeTiles[i] !=null && freeTiles[i].isVisited()){
                //removing tiles which have already been visited from list of options
                freeTiles[i] = null;
            }
        }

        return freeTiles;
    }
}
