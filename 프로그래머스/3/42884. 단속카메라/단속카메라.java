import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int lastCamera = routes[0][1];
        int answer = 1;
        for(int idx = 1; idx < routes.length; idx++) {
            if(lastCamera >= routes[idx][0])
                continue;
            
            lastCamera = routes[idx][1];
            answer++;
        }
        
        return answer;
    }
}