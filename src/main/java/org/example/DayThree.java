package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {


    public long corruptedData(String fileName) {
        List<Integer> multipliedList = new ArrayList<>();

        try {
            List<String> fileList = readfile(fileName);
            List<String> mulsList = processMulToList(fileList);

           multipliedList = getMultipliedList(mulsList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return multipliedList.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }


    public List<String> readfile(String filename) throws IOException {
        return Files.readAllLines(Path.of(filename));
    }

    public List<String> processMulToList(List<String> rawList) {
        List<String> processedList = new ArrayList<>();


        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(regex);

        for (String line : rawList) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                processedList.add(matcher.group());
            }

        }
        return processedList;
    }

    public List<Integer> getMultipliedList(List<String> mulList) {
        List<Integer> resultList = new ArrayList<>();

        for (String line : mulList) {
            String s = line.replaceAll("mul\\(|\\)", "");
            String[] numbers = s.split(",");
            int i = Integer.parseInt(numbers[0]);
            int j = Integer.parseInt(numbers[1]);
            resultList.add(i * j);
        }
        return resultList;
    }


}
