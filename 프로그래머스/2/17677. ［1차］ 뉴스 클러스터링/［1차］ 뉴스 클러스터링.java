import java.util.*;

class Solution {
    boolean isAlphabet(char ch) {
        return 'a' <= ch && ch <= 'z';
    }

    List<String> convert(String string) {
        char[] arr = string.toLowerCase().toCharArray();
        List<String> result = new ArrayList<>();

        for (int idx = 0; idx < arr.length - 1; idx++) {
            char left = arr[idx];
            char right = arr[idx + 1];

            if (isAlphabet(left) && isAlphabet(right)) {
                result.add("" + left + right);
            }
        }

        return result;
    }

    public int solution(String str1, String str2) {
        List<String> list1 = convert(str1);
        List<String> list2 = convert(str2);

        if (list1.isEmpty() && list2.isEmpty()) {
            return 65536;
        }

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for (String str : list1) {
            map1.put(str, map1.getOrDefault(str, 0) + 1);
        }

        for (String str : list2) {
            map2.put(str, map2.getOrDefault(str, 0) + 1);
        }

        Set<String> totalWords = new HashSet<>();
        totalWords.addAll(map1.keySet());
        totalWords.addAll(map2.keySet());

        int intersect = 0;
        int union = 0;

        for (String word : totalWords) {
            int count1 = map1.getOrDefault(word, 0);
            int count2 = map2.getOrDefault(word, 0);

            intersect += Math.min(count1, count2);
            union += Math.max(count1, count2);
        }

        return (int) ((double) intersect / union * 65536);
    }
}