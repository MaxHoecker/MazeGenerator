package Model.MazeGeneration;

import Model.Maze;

public interface MazeGenerator {
    public Maze generateMaze(int numberOfRows, int numberOfCols);
}
