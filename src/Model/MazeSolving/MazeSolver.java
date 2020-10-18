package Model.MazeSolving;

import Model.Cell;
import Model.Maze;

import java.util.ArrayList;

public interface MazeSolver {
    public ArrayList<Cell> solve(Maze maze);
}
