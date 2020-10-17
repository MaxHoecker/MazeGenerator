package Model;

public class Cell {
    private final int row;
    private final int col;
    private Status status;

    public Cell(int row, int col, int numberOfRows, int numberOfCols, boolean carve) {
        this.row = row;
        this.col = col;
        if (carve || (row == numberOfRows - 1 || col == numberOfCols - 1 || row == 0 || col == 0)){
            this.status = Status.WALL;
        }
        else{
            this.status = Status.PATH;
        }

        if(row == 0 && col == 1){
            this.status = Status.START;
        }
        else if(row == numberOfRows - 1 && col == numberOfCols - 2){
            this.status = Status.END;
        }

    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public String toString() {
        String output = "";
        output += "";
        if(status == Status.PATH){
            output += "   ";
        }
        else if(status == Status.WALL){
            output += "[#]";
        }
        else if(status == Status.START){
            output += "[S]";
        }
        else if(status == Status.END){
            output += "[E]";
        }
        else{
            output += "ERROR";
        }
        output += "";

        return output;
    }
}
