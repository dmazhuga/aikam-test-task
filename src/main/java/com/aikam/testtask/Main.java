package com.aikam.testtask;

import com.aikam.testtask.search.SearchOperation;

import java.io.*;

public class Main {
    private static String usageMessage = "Использование: <операция> <входной файл> <выходной файл>\nОперации: search, stat";
    private static String errorMessage = "Произошла ошибка: ";

    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                String input = FileUtility.readString(args[1]);
                String output;

                if (args[0].equals("search")) {
                    SearchOperation searchOperation = new SearchOperation();
                    output = searchOperation.execute(input);
                } else if (args[0].equals("stat")) {

                } else
                    System.out.println(usageMessage);
            } catch (FileNotFoundException e) {
                System.out.print(errorMessage);
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println(usageMessage);
    }
}
