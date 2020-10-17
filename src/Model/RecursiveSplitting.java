package Model;

import java.util.ArrayList;
import java.util.Random;

public class RecursiveSplitting implements MazeGenerator{

    @Override
    public Maze generateMaze(int numberOfRows, int numberOfCols) {
        Maze maze = new Maze(numberOfRows, numberOfCols, false );

        divide(maze);

        return maze;
    }

    //division by two walls
    //holes in walls
    //continue sub-dividing
    //completed

    //[#][#][#][#]
    //[#][#][#][#]
    //[#][#][#][#]
    //[#][#][#][#]

    private void divide(Maze maze){
        if(maze == null || maze.numberOfRows < 4 || maze.getNumberOfColumns() < 4){
            return;
        }
        Random rand = new Random();
        int split = rand.nextInt(2);

        if(split == 0){
            int splitIndex = rand.nextInt(maze.getNumberOfRows()-3)+2;

            buildWall(maze, true, splitIndex);

            recurse(maze, true, splitIndex);
        }
        else{
            int splitIndex = rand.nextInt(maze.getNumberOfColumns()-3)+2;

            buildWall(maze, false, splitIndex);

            recurse(maze, false, splitIndex);
        }


    }

    private void buildWall(Maze maze, boolean isHorizontal, int splitIndex){
        ArrayList<Cell> cells;
        Random rand = new Random();

        if(isHorizontal){
            cells = maze.getRow(splitIndex).getCells();
        }
        else{
            cells = maze.getColumn(splitIndex);
        }

        for (Cell cell : cells) {
            cell.setStatus(Status.WALL);
        }

        int path = rand.nextInt(cells.size()-2)+1;
        cells.get(path).setStatus(Status.PATH);
    }

    private void recurse(Maze maze, boolean isHorizontal, int splitIndex){
        if(isHorizontal){
            ArrayList<Row> top = new ArrayList<>();
            ArrayList<Row> bottom = new ArrayList<>();

            for(int i = 0; i < maze.getNumberOfRows(); i++){
                if (i < splitIndex){
                    top.add(maze.getRow(i));
                }
                else if(i == splitIndex){
                    top.add(maze.getRow(i));
                    bottom.add(maze.getRow(i));
                }
                else{
                    bottom.add(maze.getRow(i));
                }
            }

            divide(new Maze(top));
            divide(new Maze(bottom));
        }
        else{
            ArrayList<ArrayList<Cell>> left = new ArrayList<>();
            ArrayList<ArrayList<Cell>> right = new ArrayList<>();

            for(int i = 0; i < maze.getNumberOfColumns(); i++){
                if (i < splitIndex){
                    left.add(maze.getColumn(i));
                }
                else if(i == splitIndex){
                    left.add(maze.getColumn(i));
                    right.add(maze.getColumn(i));
                }
                else{
                    right.add(maze.getColumn(i));
                }
            }

            divide(new Maze(left, maze.getNumberOfColumns()));
            divide(new Maze(right, maze.getNumberOfColumns()));
        }
    }

}
