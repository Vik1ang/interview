package dataStructure.array.hash.q2;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    Map<Integer, Integer> freq = new HashMap<>();

    public TwoSum() {

    }

    public void add(int number) {
        // 记录 number 出现的次数
        freq.put(number, freq.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (Integer key : freq.keySet()) {
            int other = value - key;
            // 情况一
            if (other == key && freq.get(key) > 1) {
                return true;
            }
            // 情况二
            if (other != key && freq.containsKey(other)) {
                return true;
            }
        }
        return false;
    }
}
