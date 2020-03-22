package com.aikam.testtask;

import com.aikam.testtask.search.SearchOperation;
import com.aikam.testtask.stat.StatOperation;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    private static final String usageMessage = "Использование: <операция> <входной файл> <выходной файл>\nОперации: search, stat";
    private static final String outputErrorMessage = "Ошибка при записи в выходной файл";
    private static final String inputErrorMessage = "Ошибка при чтении входного файла";
    private static final String operationErrorMessage = "Неверный тип операции";
    private static final String JSONErrorMessage = "Неправильный синтаксис входного фала";
    private static final String DBErrorMessage = "Произошла ошибка в базе данных";

    public static void main(String[] args) {
        if (args.length == 3) {
            String operation = args[0];
            String inputFilePath = args[1];
            String outputFilePath = args[2];

            String input, output;

            try {
                input = FileUtility.readString(inputFilePath);

                if (operation.equals("search")) {
                    SearchOperation searchOperation = new SearchOperation();
                    output = searchOperation.execute(input);
                } else if (operation.equals("stat")) {
                    StatOperation statOperation = new StatOperation();
                    output = statOperation.execute(input);
                } else {
                    System.out.println(usageMessage);
                    output = JSONUtility.generateError(operationErrorMessage);
                }
            } catch (IOException e) {
                output = JSONUtility.generateError(inputErrorMessage);
            } catch (JSONException e) {
                output = JSONUtility.generateError(JSONErrorMessage);
            } catch (SQLException e) {
                output = JSONUtility.generateError(DBErrorMessage);
            } catch (TestTaskException e) {
                output = JSONUtility.generateError(e.getMessage());
            }

            try {
                FileUtility.writeString(outputFilePath, output);
            } catch (IOException e) {
                System.out.println(outputErrorMessage);
            }

        } else
            System.out.println(usageMessage);
    }
}
