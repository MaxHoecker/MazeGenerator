package Controller;

import Model.MazeGeneration.MazeGenerator;
import Model.MazeGeneration.RecursiveSplitting;
import View.MazeDisplay;

import java.util.Scanner;

public class CommandHandler {
    public MazeDisplay mazeDisplay;
    public MazeGenerator mazeGenerator;
    public int numberOfRows;
    public int numberOfCols;

    public static final int DIMENSIONS = 30;

    public CommandHandler() {
        this.mazeDisplay = new MazeDisplay();
        this.mazeGenerator = new RecursiveSplitting();
        this.numberOfRows = DIMENSIONS;
        this.numberOfCols = DIMENSIONS;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = "";
        CommandHandler commandHandler = new CommandHandler();
        System.out.println(commandHandler.getHelp());
        while (!input.equalsIgnoreCase("exit")){
            input = scan.nextLine();
            String [] inputs = input.split(" ");
            switch (inputs[0]){
                case "exit":
                    break;
                case "help":
                    System.out.println(commandHandler.getHelp());
                    break;
                case "generate":
                    commandHandler.generate();
                    break;
                case "setCols":
                    try {
                        int num = Integer.parseInt(inputs[1]);
                        if (num >= 3){
                            commandHandler.setCols(num);
                        }
                        else{
                            System.out.println("please enter a number greater than or equal to 3");
                        }
                    }
                    catch (Exception e){
                        System.out.println("please input a valid number");
                    }
                    break;
                case "setRows":
                    try {
                        int num = Integer.parseInt(inputs[1]);
                        if (num >= 3) {
                            commandHandler.setRows(num);
                        }
                        else{
                            System.out.println("please enter a number greater than or equal to 3");
                        }
                    }
                    catch (Exception e){
                        System.out.println("please input a valid number");
                    }
                    break;
            }
        }
    }

    public String getHelp(){
        return "type 'exit' to exit \n" +
                "'help' to print this help dialogue \n" +
                "'generate' to generate and print a maze \n" +
                "'setCols [num]' to set the number of columns in the maze \n" +
                "'setRows [num]' to set the number of columns in the maze \n";
    }

    public void generate(){
        mazeDisplay.displayMaze(mazeGenerator.generateMaze(numberOfRows, numberOfCols));
    }

    public void setCols(int col){
        this.numberOfCols = col;
    }

    public void setRows(int row){
        this.numberOfRows = row;
    }
}
