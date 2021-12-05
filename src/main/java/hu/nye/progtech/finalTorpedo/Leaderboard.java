package hu.nye.progtech.finalTorpedo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map;

public class Leaderboard {

    String playerName;
    int playerWins;

    public static void Sort(){
        Map<String, Integer> unsortedMap = new HashMap<String, Integer>();
        try {
            FileReader playerNames = new FileReader("src/main/resources/Names.txt");
            FileReader playerWins = new FileReader("src/main/resources/Wins.txt");
            BufferedReader brN = new BufferedReader(playerNames);
            BufferedReader brW = new BufferedReader(playerWins);
            String playerWin = brW.readLine();
            String playerName = brN.readLine();
            while (playerName != null || playerWin != null){
                int wins = Integer.parseInt(playerWin);
                unsortedMap.put(playerName,wins);
                playerWin = brW.readLine();
                playerName = brN.readLine();
            }
            playerNames.close();
            playerWins.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Integer> sortedMap = sortByValue(unsortedMap);
        printMap(sortedMap);
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap){
        List<Map.Entry<String,Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return 0;
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        /*
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ){
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        */
        return sortedMap;

    }

    public static void highScoreWriter(String userName){
        try {
            FileReader playerNames = new FileReader("src/main/resources/Names.txt");
            FileReader playerWins = new FileReader("src/main/resources/Wins.txt");
            int lines = 0;
            BufferedReader brN = new BufferedReader(playerNames);
            BufferedReader brW = new BufferedReader(playerWins);
            BufferedWriter bwN = null;
            BufferedWriter bwW = null;
            FileWriter fwN = null;
            FileWriter fnW = null;
            PrintWriter pwN = null;
            PrintWriter pwW = null;
            while(playerNames != null){
                lines++;
                int i=0;
                String playerWin = brW.readLine();
                String playerName = brN.readLine();
                if(userName.equals(playerName)){
                    int wins = Integer.parseInt(playerWin);
                    wins++;
                    replaceLines();
                    System.out.println("Win successfully added!");
                    break;
                }
                else if (userName != playerName){
                    fwN = new FileWriter("src/main/resources/Names.txt", true);
                    bwN = new BufferedWriter(fwN);
                    pwN = new PrintWriter(bwN);
                    pwN.println("\n" +userName);
                    System.out.println("Username successfully added!");
                    fnW = new FileWriter("src/main/resources/Wins.txt", true);
                    bwW = new BufferedWriter(fnW);
                    pwW = new PrintWriter(bwW);
                    pwW.println("1");
                    System.out.println("First win successfully added!");
                    break;
                }
            }

            playerNames.close();
            playerWins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <K, V> void printMap(Map<K, V> map){
        for (Map.Entry<K, V> entry : map.entrySet()){
            System.out.println("Player : " + entry.getKey() + " Win(s) : " + entry.getValue());
        }
    }
    public static void replaceLines() {
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("src/main/resources/Wins.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                line = String.valueOf(Integer.parseInt(line)+1); // replace the line here
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/Wins.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

}
