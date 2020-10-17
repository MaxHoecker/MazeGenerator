package Model;

import java.util.ArrayList;

public class Row {
    public ArrayList<Cell> cells;
    public int rowNum;
    public int numberOfColumns;
    public int numberOfRows;


    public Row(int rowNum,int numberOfRows, int numberOfColumns, boolean carve) {
        this.rowNum = rowNum;
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.cells = new ArrayList<>();
        for(int i = 0; i < numberOfColumns; i++){
            cells.add(new Cell(rowNum, i, numberOfRows, numberOfColumns, carve));
        }
    }

    public Row(ArrayList<Cell> cells, int numberOfRows) {
        this.cells = cells;
        this.numberOfColumns = cells.size();
        this.numberOfRows = numberOfRows;
    }

    public Status getCellStatus(int col){
        return cells.get(col).getStatus();
    }

    public void setCellStatus(int col, Status status){
        cells.get(col).setStatus(status);
    }

    public Cell getCell(int col){
        return cells.get(col);
    }

    public int getNumberOfColumns(){
        return numberOfColumns;
    }

    public ArrayList<Cell> getCells(){
        return cells;
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < numberOfColumns; i ++){
            output += cells.get(i).toString();
        }
        return output;
    }
}
