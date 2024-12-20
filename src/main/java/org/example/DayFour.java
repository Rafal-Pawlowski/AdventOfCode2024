package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFour {


    public Long wordSearch(String path) {
        long result = 0;

        try {
            List<String> rawList = fileReader(path);

            long one = searcher(rawList);
            List<String> turned = turnList(rawList);
            long two = searcher(turned);

            List<String> diagonals = diagonalList(rawList);
            long three = searcher(diagonals);

            result = one + two + three;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public List<String> fileReader(String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }

    public long searcher(List<String> rawlist) {
        String regex = "XMAS|SAMX";
        Pattern pattern = Pattern.compile(regex);
        long counter = 0;

        for (String line : rawlist) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                counter++;
                matcher.region(matcher.end() - 1, line.length());
            }
        }
        return counter;
    }


    public List<String> turnList(List<String> rawlist) {
        List<String> turned = new ArrayList<>();

        int rowLength = rawlist.get(0).length();

        for (int i = 0; i < rowLength; i++) {
            StringBuilder column = new StringBuilder();
            for (String row : rawlist) {
                if (i < row.length()) { // Dodaj tylko istniejące znaki
                    column.append(row.charAt(i));
                }
            }
            turned.add(column.toString());
        }
        return turned;
    }

    public List<String> diagonalList(List<String> rawlist) {
        int rows = rawlist.size();
        int cols = rawlist.get(0).length();
        List<String> diagonals = new ArrayList<>();

        // Left UP to Right Down
        for (int d = -rows + 1; d < cols; d++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = 0; row < rows; row++) {
                int col = d + row;
                if (col >= 0 && col < cols) {
                    diagonal.append(rawlist.get(row).charAt(col));
                }
            }
            if (diagonal.length() > 0) {
                System.out.println("Diagonal (L->R): " + diagonal);
                diagonals.add(diagonal.toString());
            }
        }

        // Right UP to Left Down
        for (int d = 0; d < rows + cols - 1; d++) {
            StringBuilder diagonal = new StringBuilder();
            for (int row = 0; row < rows; row++) {
                int col = d - row;
                if (col >= 0 && col < cols) {
                    diagonal.append(rawlist.get(row).charAt(col));
                }
            }
            if (diagonal.length() > 0) {
                System.out.println("Diagonal (R->L): " + diagonal);
                diagonals.add(diagonal.toString());
            }
        }

        return diagonals;
    }


}

