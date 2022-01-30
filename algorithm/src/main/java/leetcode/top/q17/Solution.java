package leetcode.top.q17;

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

        backtrack(combinations, phone, digits,0, new StringBuilder());

        return combinations;
    }

    private void backtrack(ArrayList<String> combinations, HashMap<Character, String> phone, String digits,int index, StringBuilder stringBuilder) {
        if (index == digits.length()) {
            combinations.add(stringBuilder.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phone.get(digit);
            int length = letters.length();

            for (int i = 0; i < length; i++) {
                stringBuilder.append(letters.charAt(i));
                backtrack(combinations, phone, digits, index + 1, stringBuilder);
                stringBuilder.deleteCharAt(index);
            }
        }
    }
}
