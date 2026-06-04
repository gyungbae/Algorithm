import java.util.*;

class Solution {
    Map<String, Integer> map = new TreeMap<>();

    boolean[] selected;

    void makeCourse(String input, int size, int depth, int startIdx) {
        if (depth == size) {
            String course = "";

            for (char ch = 'A'; ch <= 'Z'; ch++) {
                if (selected[ch - 'A']) {
                    course += ch;
                }
            }

            map.put(course, map.getOrDefault(course, 0) + 1);
            return;
        }

        for (int chIdx = startIdx; chIdx < input.length(); chIdx++) {
            int selectedIdx = input.charAt(chIdx) - 'A';

            if (selected[selectedIdx]) {
                continue;
            }

            selected[selectedIdx] = true;
            makeCourse(input, size, depth + 1, chIdx + 1);
            selected[selectedIdx] = false;
        }
    }

    public String[] solution(String[] orders, int[] course) {
        List<String> list = new ArrayList<>();

        for (int size : course) {
            map.clear();

            for (String order : orders) {
                if (order.length() < size) {
                    continue;
                }

                selected = new boolean[26];
                makeCourse(order, size, 0, 0);
            }

            int maxCount = 0;

            for (String key : map.keySet()) {
                maxCount = Math.max(maxCount, map.get(key));
            }

            if (maxCount < 2) {
                continue;
            }

            for (String key : map.keySet()) {
                if (map.get(key) == maxCount) {
                    list.add(key);
                }
            }
        }

        Collections.sort(list);

        String[] answer = new String[list.size()];

        for (int answerIdx = 0; answerIdx < list.size(); answerIdx++) {
            answer[answerIdx] = list.get(answerIdx);
        }

        return answer;
    }
}