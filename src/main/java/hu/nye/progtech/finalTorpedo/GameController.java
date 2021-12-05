package hu.nye.progtech.finalTorpedo;

public class GameController extends Map {

    private String playerName;
    private char miss = 'X';
    private char hit = '+';
    private char destroyed = '#';

    public GameController() {

    }

    public void markHit(int row, int column){
        map[row][column].markHit();
        points++;
    }

    private void generalPrintMethod(int type){
        System.out.println();
        System.out.print("  ");
        for(int i = 1; i <= columns; i++){
            System.out.print(i + " ");
        }
        System.out.println();

        int endLetterForLoop = (rows -1) + 65;
        for (int i = 65; i < endLetterForLoop; i++){
            char theChar = (char) i;
            System.out.print(theChar + " ");

            for (int j = 0; j < columns; j++){
                if (type == 0){
                    if (map[switchCounterToIntegerForArray(i)][j].isNotGuessed())
                        System.out.print("~ ");
                    else if (map[switchCounterToIntegerForArray(i)][j].checkMiss())
                        System.out.print(miss + " ");
                    else if (map[switchCounterToIntegerForArray(i)][j].checkHit())
                        System.out.print(hit + " ");
                }
                else if (type == 1){
                    if (map[switchCounterToIntegerForArray(i)][j].hasShip()){
                        if (map[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 2){
                            System.out.print("D ");
                        }
                        else if (map[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 3){
                            System.out.print("C ");
                        }
                        else if (map[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 4){
                            System.out.print("B ");
                        }
                        else if (map[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 5){
                            System.out.print("A ");
                        }
                    }
                    else if (!(map[switchCounterToIntegerForArray(i)][j].hasShip())){
                        System.out.print("~ ");
                    }
                }
                else{
                    if (map[switchCounterToIntegerForArray(i)][j].checkHit())
                        System.out.print(hit + " ");
                    else if (map[switchCounterToIntegerForArray(i)][j].hasShip()){
                        if (map[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 2){
                            System.out.print("D ");
                        }
                        else if (map[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 3){
                            System.out.print("C ");
                        }
                        else if (map[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 4){
                            System.out.print("B ");
                        }
                        else if (map[switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 5){
                            System.out.print("A ");
                        }
                    }
                    else if (!(map[switchCounterToIntegerForArray(i)][j].hasShip())){
                        System.out.print("~ ");
                    }
                    }
                }
            System.out.println();
            }
        }
        public void markMiss(int row, int column){
        map[row][column].markMiss();
        }
        public void setStatus(int row, int column, int status){
        map[row][column].setStatus(status);
        }
        public int getStatus(int row, int column){
        return map[row][column].getStatus();
        }
        public boolean alreadyGuessed(int row, int column){
        return !map[row][column].isNotGuessed();
        }
        public void setShip(int row, int column, boolean val){
        map[row][column].setShip(val);
        }
        public boolean hasShip(int row, int column){
        return map[row][column].hasShip();
        }
        public Location get(int row, int column){
        return map[row][column];
        }
        public void printStatus(){
        generalPrintMethod(0);
        }
        public void printShips(){
        generalPrintMethod(1);
        }
        public void printCombined(){
        generalPrintMethod(2);
        }

        public boolean hasLost(){
        if (points >= 17)
            return true;
        else
            return false;
        }

    public void addShip(Ship s) {
        int row = s.getRow();
        int column = s.getColumn();
        int length = s.getLength();
        int facing = s.getFacing();

        if (facing == 0) {
            for (int i = column; i < column + length; i++) {
                map[row][i].setShip(true);
                map[row][i].setLengthOfShip(length);
                map[row][i].setFacingOfShip(facing);
            }
        }
        else if (facing == 1) {
            for (int i = row; i < row + length; i++) {
                map[i][column].setShip(true);
                map[i][column].setLengthOfShip(length);
                map[i][column].setFacingOfShip(facing);
            }
        }
    }

    public int switchCounterToIntegerForArray (int val)
    {
        int toReturn = -1;
        switch (val)
        {
            case 65:    toReturn = 0;
                break;
            case 66:    toReturn = 1;
                break;
            case 67:    toReturn = 2;
                break;
            case 68:    toReturn = 3;
                break;
            case 69:    toReturn = 4;
                break;
            case 70:    toReturn = 5;
                break;
            case 71:    toReturn = 6;
                break;
            case 72:    toReturn = 7;
                break;
            case 73:    toReturn = 8;
                break;
            case 74:    toReturn = 9;
                break;
            case 75:    toReturn = 10;
                break;
            case 76:    toReturn = 11;
                break;
            case 77:    toReturn = 12;
                break;
            case 78:    toReturn = 13;
                break;
            case 79:    toReturn = 14;
                break;
            case 80:    toReturn = 15;
                break;
            case 81:    toReturn = 16;
                break;
            case 82:    toReturn = 17;
                break;
            case 83:    toReturn = 18;
                break;
            case 84:    toReturn = 19;
                break;
            case 85:    toReturn = 20;
                break;
            case 86:    toReturn = 21;
                break;
            case 87:    toReturn = 22;
                break;
            case 88:    toReturn = 23;
                break;
            case 89:    toReturn = 24;
                break;
            case 90:    toReturn = 25;
                break;
            default:    toReturn = -1;
                break;
        }

        if (toReturn == -1)
        {
            throw new IllegalArgumentException("ERROR OCCURRED IN SWITCH");
        }
        else
        {
            return toReturn;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public char getMiss() {
        return miss;
    }

    public void setMiss(char miss) {
        this.miss = miss;
    }

    public char getHit() {
        return hit;
    }

    public void setHit(char hit) {
        this.hit = hit;
    }

    public char getDestroyed() {
        return destroyed;
    }

    public void setDestroyed(char destroyed) {
        this.destroyed = destroyed;
    }
}
