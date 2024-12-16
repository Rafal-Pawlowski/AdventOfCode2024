package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayOne {

    public long processInputFile(String filePath) {
        long sum = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            List<Integer> columnResult = new ArrayList<>();
            List<Integer> column1 = new ArrayList<>();
            List<Integer> column2 = new ArrayList<>();

            for (String line : lines) {
                String[] columns = line.split("\\s+");


                try {
                    if (columns.length == 2) {
                        column1.add(Integer.parseInt(columns[0]));
                        column2.add(Integer.parseInt(columns[1]));

                    }
                } catch (NumberFormatException e) {
                    System.err.println("Niepoprawny format danych w linii: " + line);
                }
            }
            Collections.sort(column1);
            Collections.sort(column2);

            //mamy posortowane listy
            //w pętli pobieramy numer z lewej listy
            //porównujemy go w drugiej pętli w pętli z listą prawej i sprawdzamy czy wystepuje equals
            //musimy stworzyć licznik lub zrobić to streamem metodą count
            //counter pomnozyć przez liczbę z lewej listy
            //wstawić do listy result
            //na koniec zsumować listę result

            for (int i = 0; i < column1.size(); i++) {

                int number = column1.get(i);
                int count = (int) column2.stream()
                        .filter(num -> num == number)
                        .count();

                columnResult.add(number * count);
            }

            sum = columnResult.stream()
                    .mapToInt(Integer::intValue)
                    .sum();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }
}