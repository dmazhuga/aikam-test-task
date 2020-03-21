package com.aikam.testtask;

import com.aikam.testtask.search.SearchOperation;
import com.aikam.testtask.stat.StatOperation;

import java.io.*;

public class Main {
    private static final String usageMessage = "Использование: <операция> <входной файл> <выходной файл>\nОперации: search, stat";
    private static final String errorMessage = "Произошла ошибка: ";

    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                String input = FileUtility.readString(args[1]);
                String output;

                if (args[0].equals("search")) {
                    SearchOperation searchOperation = new SearchOperation();
                    output = searchOperation.execute(input);

                    FileUtility.writeString(args[2], output);
                } else if (args[0].equals("stat")) {
                    StatOperation statOperation = new StatOperation();
                    output = statOperation.execute(input);

                    FileUtility.writeString(args[2], output);
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
