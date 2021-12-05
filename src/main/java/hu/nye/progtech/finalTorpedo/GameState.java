package hu.nye.progtech.finalTorpedo;

import java.util.Scanner;

public class GameState {

    public static Scanner reader = new Scanner(System.in);

    public static void setup(Player p){
        p.playerMap.printShips();
        System.out.println();
        int counter = 1;
        int normCounter = 0;
        while (p.numOfShipsLeft() > 0){
            for (Ship s: p.ships){
                System.out.println("\nShip #" + counter + ": Length-" + s.getLength());
                int row = -1;
                int column = -1;
                int facing = -1;
                while (true){
                    System.out.println("Type in row (A-J): ");
                    String userInputRow = reader.next();
                    userInputRow = userInputRow.toUpperCase();
                    row = convertLetterToInt(userInputRow);

                    System.out.println("Type in column (1-10): ");
                    column = reader.nextInt();
                    column = convertUserColToProCol(column);

                    System.out.println("Which way is it facing? (0-Horizontal, 1-Vertical): ");
                    facing = reader.nextInt();

                    if(column >= 0 && column <= 9 && row != -1 && facing != -1){
                        if (!hasErrors(row,column,facing,p,normCounter)){
                            break;
                        }
                    }
                    System.out.println("Invalid location");
                }
                p.ships[normCounter].setLoc(row,column);
                p.ships[normCounter].setFacing(facing);
                p.playerMap.addShip(p.ships[normCounter]);
                p.playerMap.printShips();
                System.out.println();
                System.out.println("You have " + p.numOfShipsLeft() + " remaining ships to place");

                normCounter++;
                counter++;
            }
        }
    }
    public static void setupAI(Player p){
        System.out.println();
        int counter = 1;
        int normCounter = 0;
        Randomizer rand = new Randomizer();
        while (p.numOfShipsLeft() > 0){
            for(Ship s: p.ships)
            {
                int row = rand.nextInt(0, 9);
                int column = rand.nextInt(0, 9);
                int facing = rand.nextInt(0, 1);
                while (hasErrorsComp(row, column, facing, p, normCounter)){
                    row = rand.nextInt(0, 9);
                    column = rand.nextInt(0, 9);
                    facing = rand.nextInt(0, 1);
                }
                p.ships[normCounter].setLoc(row,column);
                p.ships[normCounter].setFacing(facing);
                p.playerMap.addShip(p.ships[normCounter]);

                normCounter++;
                counter++;
            }

        }
    }
    public static String askForGuess(Player p, Player opp){
        System.out.println("Guesses: ");
        p.enemyMap.printStatus();

        int row = -1;
        int column = -1;

        String oldRow = "Z";
        int oldCol = -1;

        while(true){
            System.out.println("Type in row (A-J): ");
            String userInputRow = reader.next();
            userInputRow = userInputRow.toUpperCase();
            oldRow = userInputRow;
            row = convertLetterToInt(userInputRow);

            System.out.println("Type in column (1-10): ");
            column = reader.nextInt();
            oldCol = column;
            column = convertUserColToProCol(column);

            if (column >= 0 && column <= 9 && row != -1)
                break;

            System.out.println("Invalid location!");
        }

        if (opp.playerMap.hasShip(row, column)){
            p.enemyMap.markHit(row, column);
            opp.playerMap.markHit(row, column);
            return "User hit at " + oldRow + oldCol;
        }
        else {
            p.enemyMap.markMiss(row,column);
            opp.playerMap.markMiss(row, column);
            return "User miss at " + oldRow + oldCol;
        }
    }
    public static void compMakeGuess(Player comp, Player user){
        Randomizer rand = new Randomizer();
        int row = rand.nextInt(0, 9);
        int column = rand.nextInt(0, 9);

        while (comp.enemyMap.alreadyGuessed(row, column)){
            row = rand.nextInt(0, 9);
            column = rand.nextInt(0, 9);
        }

        if (user.playerMap.hasShip(row, column)){
            comp.enemyMap.markHit(row,column);
            user.playerMap.markHit(row,column);
            System.out.println("Ai hit at "+ convertIntToLetter(row) + convertCompColToRegular(column));
        }
        else {
            comp.enemyMap.markMiss(row, column);
            user.playerMap.markMiss(row,column);
            System.out.println("Ai miss at "+ convertIntToLetter(row) + convertCompColToRegular(column));
        }
        System.out.println("\nYour board");
        reader.nextLine();
        user.playerMap.printCombined();
        System.out.println("Continue...");
        reader.nextLine();
    }
    private static boolean hasErrors(int row, int column, int facing, Player p, int count){
        int length = p.ships[count].getLength();
        if (facing == 0){
            int checker = length + column;
            if (checker > 10){
                System.out.println("Ship does not fit");
                return true;
            }
        }
        if(facing == 1){
            int checker = length + row;
            if (checker > 10){
                System.out.println("Ship does not fit");
                return true;
            }
        }
        if (facing == 0){
            for (int i = column;i < column+length; i++){
                if(p.playerMap.hasShip(row, i)){
                    System.out.println("Ship already exists at this location");
                    return true;
                }
            }
        } else if (facing == 1) {
            for (int i = row; i < row+length; i++){
                if(p.playerMap.hasShip(i, column)){
                    System.out.println("Ship already exists at this location");
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean hasErrorsComp(int row, int column, int facing, Player p, int count){
        int length = p.ships[count].getLength();
        if (facing == 0){
            int checker = length + column;
            if (checker > 10)
                return true;
        }
        if (facing == 1){
            int checker = length + row;
            if (checker > 10)
                return true;
        }
        if (facing == 0){
            for (int i = column; i < column+length; i++){
                if(p.playerMap.hasShip(row, i))
                    return true;
            }
        }
        else if (facing == 1){
            for (int i = row; i < row+length; i++){
                if(p.playerMap.hasShip(i,column))
                    return true;
            }
        }
        return false;
    }
    private static int convertLetterToInt(String val)
    {
        int toReturn = -1;
        switch (val)
        {
            case "A":   toReturn = 0;
                break;
            case "B":   toReturn = 1;
                break;
            case "C":   toReturn = 2;
                break;
            case "D":   toReturn = 3;
                break;
            case "E":   toReturn = 4;
                break;
            case "F":   toReturn = 5;
                break;
            case "G":   toReturn = 6;
                break;
            case "H":   toReturn = 7;
                break;
            case "I":   toReturn = 8;
                break;
            case "J":   toReturn = 9;
                break;
            default:    toReturn = -1;
                break;
        }

        return toReturn;
    }

    private static String convertIntToLetter(int val)
    {
        String toReturn = "Z";
        switch (val)
        {
            case 0:   toReturn = "A";
                break;
            case 1:   toReturn = "B";
                break;
            case 2:   toReturn = "C";
                break;
            case 3:   toReturn = "D";
                break;
            case 4:   toReturn = "E";
                break;
            case 5:   toReturn = "F";
                break;
            case 6:   toReturn = "G";
                break;
            case 7:   toReturn = "H";
                break;
            case 8:   toReturn = "I";
                break;
            case 9:   toReturn = "J";
                break;
            default:    toReturn = "Z";
                break;
        }

        return toReturn;
    }

    private static int convertUserColToProCol(int val)
    {
        int toReturn = -1;
        switch (val)
        {
            case 1:   toReturn = 0;
                break;
            case 2:   toReturn = 1;
                break;
            case 3:   toReturn = 2;
                break;
            case 4:   toReturn = 3;
                break;
            case 5:   toReturn = 4;
                break;
            case 6:   toReturn = 5;
                break;
            case 7:   toReturn = 6;
                break;
            case 8:   toReturn = 7;
                break;
            case 9:   toReturn = 8;
                break;
            case 10:   toReturn = 9;
                break;
            default:    toReturn = -1;
                break;
        }

        return toReturn;
    }

    private static int convertCompColToRegular(int val)
    {
        int toReturn = -1;
        switch (val)
        {
            case 0:   toReturn = 1;
                break;
            case 1:   toReturn = 2;
                break;
            case 2:   toReturn = 3;
                break;
            case 3:   toReturn = 4;
                break;
            case 4:   toReturn = 5;
                break;
            case 5:   toReturn = 6;
                break;
            case 6:   toReturn = 7;
                break;
            case 7:   toReturn = 8;
                break;
            case 8:   toReturn = 9;
                break;
            case 9:   toReturn = 10;
                break;
            default:    toReturn = -1;
                break;
        }

        return toReturn;
    }

}
