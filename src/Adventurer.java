import sun.rmi.runtime.Log;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by silver on 25/02/2017.
 */
public class Adventurer {
    private final Maze maze;
    private Point location;
    ArrayList<LogEntry> travelLog;
    private boolean fork;
    private int moveCounter = 0;

    public Adventurer(Maze maze) {
        this.maze = maze;
        travelLog = new ArrayList<LogEntry>();
    }

    public void startQuest(Point location) {
        moveTo(location);
    }

    private ArrayList<LogEntry> moveTo(Point nextLocation) {

        location = nextLocation;
        moveCounter++;
        fork = false;

        //check if this is a win
        if(location==maze.getEndPoint()){
            //adventure has completed his quest and returns his travel log
            return endQuest();
        }

        //check the next tile's possible moves
        MazeTile[] moveOptions = getMoveOptions(location);

        //no options means the current tile is a dead end.
        if(moveOptions.length == 0){
            Point fallBackPoint = getFallBack();
            if(fallBackPoint != null){
                moveTo(fallBackPoint);
            }
            else{
                return null;
            }
        }
        //if there is more than one move option then this tile is a fork
        else if(moveOptions.length>1){
            fork = true;
        }
        //keep track of each move to show final solution and note down any forks in the tunnel to fall back to in event of a dead-end
        logMove(moveCounter,location,fork);

        //mark the current tile as visited
        maze.markTile(location);

        //choose a move from the move counter
        Point nextMove = chooseMove(moveOptions);

        //play again
        moveTo(nextMove);
        return null;
    }

    private Point getFallBack() {
        //preparing to iterate backwards to get last fall back point
        ListIterator<LogEntry> entries = travelLog.listIterator(travelLog.size());
        while (entries.hasPrevious()){
            LogEntry currentEntry = entries.previous();
            if(currentEntry.isFork()){
                //removing all steps to last fall back point
                int entryIndex = travelLog.indexOf(currentEntry);
                travelLog.remove(entryIndex);
                return currentEntry.getLocation();
            }
        }
        return null;
    }

    private ArrayList<LogEntry> endQuest() {
        System.out.println("Exit located");
        return travelLog;
    }

    private Point chooseMove(MazeTile[] moveOptions) {
        // dumb selection , choosing the first available
        // possibly could be improved by checking if any of the options is the exit
        //todo check the above later
        Point result = moveOptions[0].getPosition();

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
            if(freeTiles[i].isVisited()){
                //removing tiles which have already been visited from list of options
                freeTiles[i] = null;
            }
        }

        return freeTiles;
    }
}
