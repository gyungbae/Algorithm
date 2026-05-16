import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int max = 0;
        for (int fruit : tangerine) {
            max = Math.max(fruit, max);
        }

        int[] count = new int[max + 1];
        for (int fruit : tangerine) {
            count[fruit]++;
        }

        Arrays.sort(count);

        int answer = 0;
        int selected = 0;

        for (int idx = count.length - 1; idx >= 0; idx--) {
            if (count[idx] == 0) 
                break;

            selected += count[idx];
            answer++;

            if (selected >= k) 
                break;
        }

        return answer;
    }
}