import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        int size = citations.length;

        for(int idx = 0; idx < size; idx++) {
            int h = size - idx;

            if(citations[idx] >= h) {
                return h;
            }
        }

        return 0;
    }
}