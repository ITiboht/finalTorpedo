package hu.nye.progtech.finalTorpedo;

public class Map<S, I extends Number> {

    public Location[][] map;
    public int points;

    public int rows = 10;
    public int columns = 10;

    public Map(){

        map = new Location[rows][columns];

        for (int row = 0; row < map.length; row++)
        {
            for (int column = 0; column < map.length; column++)
            {
                Location tempLoc = new Location();
                map[row][column] = tempLoc;
            }
        }
    }

    public int numRows(){
        return rows;
    }
    public int numColumns(){
        return columns;
    }

}
