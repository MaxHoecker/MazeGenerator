package Model.MazeGeneration;

import Model.Cell;
import Model.Maze;
import Model.Row;
import Model.Status;

import java.util.ArrayList;
import java.util.Random;

public class RecursiveSplitting implements MazeGenerator{

    @Override
    public Maze generateMaze(int numberOfRows, int numberOfCols) {
        Maze maze = new Maze(numberOfRows, numberOfCols, false );

        divide(maze);

        return maze;
    }


    private void divide(Maze maze){
        System.out.println(maze);

        if(maze == null || (maze.getNumberOfRows() < 5 && maze.getNumberOfColumns() < 5)){
            return;
        }

        Random rand = new Random();
        int split = rand.nextInt(2);
        if(maze.getNumberOfRows() < 5){
            split = 1;
        }
        else if(maze.getNumberOfColumns() < 5){
            split = 0;
        }
        else {
            split = rand.nextInt(2);
        }


        if(split == 0){
            int splitIndex = getSplitIndex(maze, true, 0);

            buildWall(maze, true, splitIndex);

            recurse(maze, true, splitIndex);
        }
        else{
            int splitIndex = getSplitIndex(maze, false, 0);

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

        for (int i = 1; i < cells.size() - 1; i++) {
            cells.get(i).setStatus(Status.WALL);
        }

        int path = rand.nextInt(cells.size()-2)+1;
        if(cells.size() % 2 == 1 && path == cells.size() / 2 && cells.size() > 3){
            path -= 1;
        }
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

            divide(new Maze(left, maze.getNumberOfRows()));
            divide(new Maze(right, maze.getNumberOfRows()));
        }
    }

    private int getSplitIndex(Maze maze,boolean isHorizontal, int bias){
        int splitIndex = 0;
        Random rand = new Random();

        if(maze.getNumberOfRows() == 5 || maze.getNumberOfColumns() == 5){
            return 2;
        }
        else{
            if(isHorizontal){
                splitIndex = rand.nextInt(maze.getNumberOfRows()-4-bias)+2;
            }
            else{
                splitIndex = rand.nextInt(maze.getNumberOfColumns()-4-bias)+2;
            }
        }
        ArrayList<Cell> cells;

        if(isHorizontal){
            cells = maze.getRow(splitIndex).getCells();
        }
        else {
            cells = maze.getColumn(splitIndex);
        }

        if(cells.get(0).getStatus() != Status.WALL || cells.get(cells.size()-1).getStatus() != Status.WALL){
            try{
                return getSplitIndex(maze, isHorizontal, bias - 1);
            }
            catch(Exception e){
                return getSplitIndex(maze, isHorizontal, bias + 1);
            }
        }
        else{
            return splitIndex;
        }

    }

}
