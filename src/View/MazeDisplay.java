package View;

import Model.Maze;

public class MazeDisplay {
    public MazeDisplay() {

    }

    public void displayMaze(Maze maze){
        System.out.println(maze.toString());
    }
}
