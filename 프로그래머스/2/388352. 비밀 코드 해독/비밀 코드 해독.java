import java.util.*;

class Solution {
    int n, m;
    int[][] q;
    int[] ans;
    
    int answer;
    boolean[] selected;
    
    void select(int from, int depth) {
        if(depth == 5) {
            int[] code = new int[5];
            int idx = 0;
            for(int num = 1; num <= n; num++) {
                if(selected[num])
                    code[idx++] = num;
            }
            
            if(isPossible(code))
                answer++;
            
            return;
        }
        
        for(int num = from + 1; num <= n; num++) {
            if(selected[num])
                continue;
            
            selected[num] = true;
            select(num, depth + 1);
            selected[num] = false;
        }
    }
    
    boolean isPossible(int[] code) {
        for(int idx = 0; idx < m; idx++) {
            int[] question = q[idx];
            int count = 0;
            
            for(int codeIdx = 0; codeIdx < 5; codeIdx++) {
                int codeNum = code[codeIdx];
                for(int questionIdx = 0; questionIdx < 5; questionIdx++) {
                    if(codeNum == question[questionIdx]) {
                        count++;
                        break;
                    }    
                }
            }
            
            if(count != ans[idx])
                return false;
        }
        
        return true;
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        m = q.length;
        this. q = q;
        this.ans = ans;
        
        for(int num : ans) {
            if(num == 5)
                return 1;
        }
        
        selected = new boolean[n + 1];
        select(0, 0);
        
        return answer;
    }
}