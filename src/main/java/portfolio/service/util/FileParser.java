package portfolio.service.util;

import portfolio.service.exception.FileEmptyRuntimeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class FileParser {

    public static List<Double> parseFile(String nameFile) {
        Scanner scanner = getScanner(nameFile);
        List<Double> fileValues = new ArrayList<>();

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                fileValues.add(scanner.nextDouble());
            } else {
                scanner.next();
            }
        }
        return fileValues;
    }

    private static Scanner getScanner(String nameFile) {
        File file = new File(nameFile);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner == null) {
            throw new FileEmptyRuntimeException("File " + nameFile + " is empty");
        }
        return scanner;
    }
}
