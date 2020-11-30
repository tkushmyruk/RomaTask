package ua.taras.kushmyruk;

import java.util.ArrayList;
import java.util.List;

public class StringValidator {
    private static List<List<String>> patterns = new ArrayList<List<String>>();

    public static void main(String[] args) {
        createPattern("Best", new String[]{"banks"}, "China");
        System.out.println(validate("Best banks in China"));
    }

    public static void createPattern(String top, String[] params, String in) {
        List<String> pattern = new ArrayList<String>();
        pattern.add(top.toLowerCase());
        for (String s : params) {
            pattern.add(s.toLowerCase());
        }
        pattern.add("in");
        pattern.add(in.toLowerCase());

        patterns.add(pattern);
    }

    public static boolean validate(String query) {
        String[] criteries = query.split(" ");
        for (int i = 0; i < criteries.length; i++) {
            criteries[i] = criteries[i].toLowerCase();
        }
        if (!topFormatValidate(criteries[0])) {
            return false;
        }
        List<List<String>> validatedPattern = patternValidate(criteries);
        if (!topValidate(criteries[0], validatedPattern)) {
            return false;
        }
        if (!paramsValidate(validatedPattern, criteries)) {
            return false;
        }
        if (!inValidate(validatedPattern, criteries)) {
            return false;
        }
        return true;

    }

    public static boolean topFormatValidate(String top) {
        if (top.equals("best") || top.equals("most") || top.equals("less")) {
            return true;
        }
        int topNumber = Integer.parseInt(top);
        if (topNumber > 0) {
            return true;
        }

        return false;
    }

    public static List<List<String>> patternValidate(String[] params) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < patterns.size(); i++) {
            if (patterns.get(i).size() == params.length) {
                list.add(patterns.get(i));
            }
        }
        return list;
    }

    public static boolean topValidate(String top, List<List<String>> pattern) {
        for (int i = 0; i < pattern.size(); i++) {
            if (pattern.get(i).get(0).equals(top)) {
                return true;
            }
        }
        return false;
    }

    public static boolean paramsValidate(List<List<String>> pattern, String[] criteries) {
        for (int i = 0; i < pattern.size(); i++) {
            for (int j = 1; j < criteries.length - 2; j++) {
                int counter = 0;
                if (criteries[j].equals(pattern.get(i).get(j))) {
                    counter++;
                }
                if (counter == criteries.length - 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean inValidate(List<List<String>> pattern, String[] criteries) {
        for (int i = 0; i < pattern.size(); i++) {
            if (pattern.get(i).get(pattern.get(i).size() - 1).equals(criteries[criteries.length - 1])) {
                return true;
            }
        }
        return false;
    }
}
