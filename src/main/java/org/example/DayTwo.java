package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DayTwo {

    public long safeReportsCounterMethod(String filePath) {

        long result = 0;

        try {
            List<String> raportList = readFile(filePath);

            List<List<Integer>> reports = processReportsToLevels(raportList);


            result = reports.stream()
                    .filter(report -> {
                        boolean isValid = isListValid(report);
                        boolean canBeFixed = canBeMadeSafe(report);
                        if (!isValid && !canBeFixed) {
                            System.out.println("Unsafe report: " + report);
                        }
                        return isValid || canBeFixed;
                    })
                    .count();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        return result;
    }

    private List<String> readFile(String inputPath) throws IOException {
        return Files.readAllLines(Path.of(inputPath));
    }

    private List<List<Integer>> processReportsToLevels(List<String> raportList) {

        List<List<Integer>> listOfReports = new ArrayList<>();


        for (String line : raportList) {
            String[] reports = line.split("\\s+");
            try {
                List<Integer> levels = new ArrayList<>();
                for (int i = 0; i < reports.length; i++) {
                    levels.add(Integer.parseInt(reports[i]));
                }
                listOfReports.add(levels);

            } catch (NumberFormatException e) {
                System.err.println("Niepoprawny format danych w linii: " + line);
            }
        }
        return listOfReports;
    }


    private boolean canBeMadeSafe(List<Integer> list) {
        if (list.size() <= 2) return true;

        for (int i = 0; i < list.size(); i++) {
            List<Integer> modifiedList = new ArrayList<>(list);
            modifiedList.remove(i);

            if (isListValid(modifiedList)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isListValid(List<Integer> list) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 1; i < list.size(); i++) {
            int diff = list.get(i) - list.get(i - 1);

            if (diff < -3 || diff > 3) return false;
            if (diff == 0) return false;

            if (diff > 0) isDecreasing = false;
            if (diff < 0) isIncreasing = false;
        }

        return isIncreasing || isDecreasing;
    }
}
