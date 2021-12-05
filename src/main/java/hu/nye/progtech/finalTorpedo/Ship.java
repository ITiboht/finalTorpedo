package hu.nye.progtech.finalTorpedo;

public class Ship {
    private int row;
    private int column;
    private int length;
    private int facing;

    public static final int unset = -1;
    public static final int horizontal = 0;
    public static final int vertical = 1;

    public Ship(int length) {
        this.row = -1;
        this.column = -1;
        this.length = length;
        this.facing = unset;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing){
        if (facing != unset && facing != horizontal && facing != vertical)
            throw new IllegalArgumentException("Invalid facing.");
        this.facing = facing;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "row=" + row +
                ", column=" + column +
                ", length=" + length +
                ", facing=" + facing +
                '}';
    }

    public boolean isLocationSet(){
        if(row == -1 || column == -1)
            return false;
        else
            return true;
    }

    public boolean isFacingSet(){
        if(facing == unset)
            return false;
        else
            return true;
    }

    public void setLoc(int row, int column){
        this.row = row;
        this.column = column;
    }

    private String facingToString(){
        if (facing == unset)
            return "unset";
        else if (facing == horizontal)
            return "horizontal";
        else
            return "vertical";
    }

}
