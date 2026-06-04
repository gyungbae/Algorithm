import java.util.*;

class Solution {
    String[] convert(String str) {
        str = str.toLowerCase();

        int firstNumIdx = -1;
        int lastNumIdx = str.length();

        for (int idx = 0; idx < str.length(); idx++) {
            char ch = str.charAt(idx);

            if (firstNumIdx == -1 && '0' <= ch && ch <= '9') {
                firstNumIdx = idx;
            }

            if (firstNumIdx != -1 && !('0' <= ch && ch <= '9')) {
                lastNumIdx = idx;
                break;
            }
        }

        String[] result = new String[2];
        result[0] = str.substring(0, firstNumIdx);
        result[1] = str.substring(firstNumIdx, lastNumIdx);

        return result;
    }

    public String[] solution(String[] files) {
        Arrays.sort(files, (o1, o2) -> {
            String[] o1Arr = convert(o1);
            String[] o2Arr = convert(o2);

            if (o1Arr[0].equals(o2Arr[0])) {
                return Integer.parseInt(o1Arr[1]) - Integer.parseInt(o2Arr[1]);
            }

            return o1Arr[0].compareTo(o2Arr[0]);
        });

        return files;
    }
}