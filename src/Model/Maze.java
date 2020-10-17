package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Maze {
    public ArrayList<Row> rows;
    public int numberOfRows;
    public HashMap<Cell, Cell> adjacencyList;

    public Maze(int numberOfRows, int numberOfColumns, boolean carve) {
        ArrayList<Row> rows = new ArrayList<>();
        for(int i = 0; i < numberOfRows; i++){
            rows.add(new Row(i, numberOfRows, numberOfColumns, carve));
        }
        this.rows = rows;
        this.numberOfRows = numberOfRows;
    }

    public Maze(ArrayList<Row> rows) {
        this.rows = rows;
        this.numberOfRows = rows.size();
    }

    public Maze(ArrayList<ArrayList<Cell>> cols, int numberOfRows){
        this.numberOfRows = numberOfRows;
        ArrayList<Row> rows = new ArrayList<>();

        for (int i = 0; i < numberOfRows; i ++){
            ArrayList<Cell> row = new ArrayList<>();
            for(int j = 0; j < cols.size(); j++){
                row.add(cols.get(j).get(i));
            }
            rows.add(new Row(row, numberOfRows));
        }

        this.rows = rows;
    }

    public Status getCellStatus(int row, int col) {
        return rows.get(row).getCellStatus(col);
    }

    public void setCellStatus(int row, int col, Status status){
        rows.get(row).setCellStatus(col, status);
    }

    public Cell getCell(int row, int col){
        return rows.get(row).getCell(col);
    }

    public int getNumberOfRows(){
        return numberOfRows;
    }

    public int getNumberOfColumns(){
        return rows.get(0).getNumberOfColumns();
    }

    public ArrayList<Cell> getColumn(int col){
        ArrayList<Cell> cols = new ArrayList<>();
        for(int i = 0; i < numberOfRows; i++){
            cols.add(rows.get(i).getCell(col));
        }
        return cols;
    }

    public Row getRow(int row){
        return rows.get(row);
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < numberOfRows; i ++){
            output += rows.get(i).toString() + "\n";
        }
        return output;
    }
}
