package org.example;

import java.io.*;
import java.util.*;

public class Main {
    private static String pathToCsv = "C:\\Users\\nikol\\OneDrive\\Рабочий стол\\airports.csv";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s = "";
        int col = 0;
        try {
            col = Integer.parseInt(args[0]) - 1;
        } catch (NullPointerException e){
            System.out.println("Ошибка");
        }
        List<String[]> resultList = new ArrayList<>();

        while(true) {
            s = reader.readLine().toLowerCase();

            if(s.equals("!quit")){
                break;
            }

            long m = System.currentTimeMillis();

            resultList = ReadCSV(col, s, pathToCsv);

            int time = (int) (System.currentTimeMillis() - m);

            sortString(resultList, col);

            for (String[] str:
                    resultList) {
                System.out.println(str[col] + Arrays.toString(str));
            }

            System.out.println("Кол-во найденых строк: " + resultList.size() + " Время выполнения: " + time + " ms");
        }

        reader.close();
    }

    public static List<String[]> ReadCSV(int col, String str, String pathToCsv) throws IOException {
        List<String[]> readRows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(pathToCsv));

        String row;
        String[] data;

        while ((row = reader.readLine()) != null) {
            data = row.split(",");
            if ((data[col].toLowerCase().indexOf("\"") == 0 && data[col].toLowerCase().indexOf(str) == 1) || data[col].toLowerCase().indexOf(str) == 0) {
                readRows.add(data);
            }
        }

        return readRows;
    }

    public static void sortString(List<String[]> resultList, int col){
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < resultList.size() - 1; i++) {
                if (resultList.get(i)[col].compareTo(resultList.get(i+1)[col]) > 0) {
                    Collections.swap(resultList, i, i+1);
                    sorted = false;
                }
            }
        }
    }
}