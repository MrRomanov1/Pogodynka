package pl.piotr_romanczak;

import java.util.HashMap;

public class AdditionalMethods {
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }

    public static String removePolishCharacters(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case 'ą':
                    stringBuilder.append('a');
                    break;
                case 'ć':
                    stringBuilder.append('c');
                    break;
                case 'ę':
                    stringBuilder.append('e');
                    break;
                case 'ł':
                    stringBuilder.append('l');
                    break;
                case 'ń':
                    stringBuilder.append('n');
                    break;
                case 'ó':
                    stringBuilder.append('o');
                    break;
                case 'ś':
                    stringBuilder.append('s');
                    break;
                case 'ź':
                    stringBuilder.append('z');
                    break;
                case 'ż':
                    stringBuilder.append('z');
                    break;
                case 'Ą':
                    stringBuilder.append('A');
                    break;
                case 'Ć':
                    stringBuilder.append('C');
                    break;
                case 'Ę':
                    stringBuilder.append('E');
                    break;
                case 'Ł':
                    stringBuilder.append('L');
                    break;
                case 'Ń':
                    stringBuilder.append('N');
                    break;
                case 'Ó':
                    stringBuilder.append('O');
                    break;
                case 'Ś':
                    stringBuilder.append('S');
                    break;
                case 'Ź':
                    stringBuilder.append('Z');
                    break;
                case 'Ż':
                    stringBuilder.append('Z');
                    break;
                default:
                    stringBuilder.append(input.charAt(i));
                    break;
            }
        }
        return stringBuilder.toString();
    }
    public static HashMap<String, String> setCityNamesWithoutPolishCharacters(HashMap<String, String> cityNames) {
        HashMap<String, String> result = new HashMap<>();
        for (String key : cityNames.keySet()) {
            result.put(key, removePolishCharacters(key));
        }
        return result;
    }
}
