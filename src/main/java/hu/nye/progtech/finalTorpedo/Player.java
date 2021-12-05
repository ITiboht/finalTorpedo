package hu.nye.progtech.finalTorpedo;

public class Player {
    private static final int[] ship_length = {2, 3, 3, 4, 5};
    private static final int num_of_ships = 5;

    public Ship[] ships;
    public GameController playerMap;
    public GameController enemyMap;

    public Player(){
        //if (num_of_ships != 5)
        ships = new Ship[num_of_ships];
        for (int i = 0; i < num_of_ships; i++)
        {
            Ship tempShip = new Ship(ship_length[i]);
            ships[i] = tempShip;
        }

        playerMap = new GameController();
        enemyMap = new GameController();
    }

    public void addShips(){
        for (Ship s: ships)
        playerMap.addShip(s);
    }

    public int numOfShipsLeft(){
        int counter = 5;
        for (Ship s: ships)
        {
            if (s.isLocationSet() && s.isFacingSet())
                counter--;
        }
        return counter;
    }

    public void chooseShipLoc(Ship s, int row, int column, int facing){
        s.setLoc(row, column);
        s.setFacing(facing);
        playerMap.addShip(s);
    }

}
