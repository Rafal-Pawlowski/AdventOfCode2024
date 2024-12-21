package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DayFive {

    public long pageOrderCorrectnessChecker(String url) {
        try {
            List<String> lines = Files.readAllLines(Path.of(url));
            List<String> rules = new ArrayList<>();
            List<List<Integer>> updates = new ArrayList<>();

            boolean isRulesSection = true;
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    isRulesSection = false;
                } else if (isRulesSection) {
                    rules.add(line);
                } else {
                    updates.add(parseUpdate(line));
                }
            }

            Map<Integer, Set<Integer>> graph = buildDependencyGraph(rules);

            long sumOfMiddles = 0;
            for (List<Integer> update : updates) {
                if (isUpdateValid(update, graph)) {
                    sumOfMiddles += findMiddle(update);
                }
            }
            return sumOfMiddles;

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }

    private List<Integer> parseUpdate(String line) {
        String[] parts = line.split(",");
        List<Integer> update = new ArrayList<>();
        for (String part : parts) {
            update.add(Integer.parseInt(part.trim()));
        }
        return update;
    }

    private Map<Integer, Set<Integer>> buildDependencyGraph(List<String> rules) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (String rule : rules) {
            String[] parts = rule.split("\\|");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            graph.computeIfAbsent(x, k -> new HashSet<>()).add(y);
        }
        return graph;
    }

    private boolean isUpdateValid(List<Integer> update, Map<Integer, Set<Integer>> graph) {
        for (int i = 0; i < update.size(); i++) {
            for (int j = i + 1; j < update.size(); j++) {
                int current = update.get(i);
                int next = update.get(j);
                if (graph.containsKey(next) && graph.get(next).contains(current)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int findMiddle(List<Integer> update) {
        int middleIndex = update.size() / 2;
        return update.get(middleIndex);
    }
}
