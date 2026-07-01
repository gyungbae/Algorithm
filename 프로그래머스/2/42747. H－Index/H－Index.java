import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int left = 0;
        int right = citations.length;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            int count = 0;
            for (int citation : citations) {
                if (citation >= mid) {
                    count++;
                }
            }

            if (count >= mid) {
                answer = mid;
                left = mid + 1; 
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }
}