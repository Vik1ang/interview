package top100.q17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }

        HashMap<Character, String> phone = new HashMap<>();
        phone.put('2', "abc");
        phone.put('3', "def");
        phone.put('4', "ghi");
        phone.put('5', "jkl");
        phone.put('6', "mno");
        phone.put('7', "pqrs");
        phone.put('8', "tuv");
        phone.put('9', "wxyz");

        backtrack(combinations, phone, digits, 0, new StringBuffer());
        return combinations;
    }

    private void backtrack(ArrayList<String> combinations, HashMap<Character, String> phone, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phone.get(digit);
            int lettersCount = letters.length();

            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phone, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}
