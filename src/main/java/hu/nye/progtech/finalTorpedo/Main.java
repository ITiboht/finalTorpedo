package hu.nye.progtech.finalTorpedo;

import java.util.Scanner;

import static hu.nye.progtech.finalTorpedo.GameState.*;
import static hu.nye.progtech.finalTorpedo.Leaderboard.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to BattleShip!");
        System.out.println("Main Menu");
        System.out.println("[1] New Game");
        System.out.println("[2] Leaderboard");
        System.out.println("[3] Exit");
        Scanner in = new Scanner(System.in);
        String menu = in.nextLine();

        switch (menu){
            case "1":{
                System.out.println("Enter your name: ");
                String playerName = in.nextLine();
                Player userPlayer = new Player();
                setup(userPlayer);
                Player ai = new Player();
                setupAI(ai);
                System.out.println("Enable debug mode? (1-yes, anything else-no)");
                int Debug = in.nextInt();
                if(Debug == 1)
                    ai.playerMap.printShips();
                else break;
                String result = "";
                while (true){
                    System.out.println(result);
                    System.out.println(("Player's turn: "));
                    result = askForGuess(userPlayer, ai);
                    if (userPlayer.playerMap.hasLost()){
                        System.out.println("Computer hit!   The player lost");
                        break;
                    }
                    else if (ai.playerMap.hasLost()){
                        System.out.println("Hit!   The computer lost");
                        highScoreWriter(playerName);
                        break;
                    }
                    System.out.println("Computer's turn");
                    compMakeGuess(ai, userPlayer);
                }
                break;
            }
            case "2":{
                Sort();
                break;
            }
            case "3":{
                System.out.println("Exit");
                break;
            }
            default:{
                System.out.println("Enter your name: ");
                String playerName = in.nextLine();
                highScoreWriter(playerName);
                System.out.println("No Options were selected.");
                break;
            }
        }

    }
}
