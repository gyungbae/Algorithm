import java.util.*;

class Solution {
    List<int[]> list = new ArrayList<>();
    
    void move(int count, int start, int mid, int end) {
        if(count == 0)
            return;
        
        move(count - 1, start, end, mid);
        list.add(new int[]{start, end});
        move(count - 1, mid, start, end);
    }
    
    public int[][] solution(int n) {
        move(n, 1, 2, 3);
        
        int[][] answer = new int[list.size()][2];
        
        for(int idx = 0; idx < list.size(); idx++) {
            answer[idx] = list.get(idx);
        }
        
        return answer;
    }
}