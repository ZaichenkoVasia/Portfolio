package portfolio.util;

import portfolio.exception.FileEmptyRuntimeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class FileParser {

    public static ArrayList<Double> parseFile(String nameFile) {
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
        ArrayList<Double> fileValues = new ArrayList<>();

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                fileValues.add(scanner.nextDouble());
            } else {
                scanner.next();
            }
        }
        return fileValues;
    }
}
