import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];

        for(int idx = 0; idx < numbers.length; idx++) {
            arr[idx] = numbers[idx] + "";
        }

        Arrays.sort(arr, (o1, o2) -> {
            String str1 = o1 + o2;
            String str2 = o2 + o1;
            return str2.compareTo(str1);
        });

        if(arr[0].equals("0")) { 
            return "0";
        }

        StringBuilder answer = new StringBuilder();

        for(String str : arr) {
            answer.append(str);
        }

        return answer.toString();
    }
}