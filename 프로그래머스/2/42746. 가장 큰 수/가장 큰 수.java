import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];

        int idx = 0;
        for(int num : numbers) {
            arr[idx++] = String.valueOf(num);
        }

        Arrays.sort(arr, (o1, o2) -> {
            return (o2 + o1).compareTo(o1 + o2);
        });

        if(arr[0].equals("0"))
            return "0";

        StringBuilder answer = new StringBuilder();

        for(String num : arr) {
            answer.append(num);
        }

        return answer.toString();
    }
}