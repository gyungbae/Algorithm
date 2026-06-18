import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        
        for(int idx = 0; idx < n; idx++) {
            prev[idx] = idx - 1;
            next[idx] = idx + 1;
        }
        next[n - 1] = -1;
        
        boolean[] removed = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        
        for(String order : cmd) {
            String[] info = order.split(" ");
            String command = info[0];
            
            if(command.equals("U")) {
                int count = Integer.parseInt(info[1]);
                
                while(count > 0) {
                    k = prev[k];
                    count--;
                }
                
            } else if(command.equals("D")) {
                int count = Integer.parseInt(info[1]);
                
                while(count > 0) {
                    k = next[k];
                    count--;
                }
            } else if(command.equals("C")) {
                removed[k] = true;
                stack.push(k);
                
                int prevNode = prev[k];
                int nextNode = next[k];
                
                if(prevNode != -1) {
                    next[prevNode] = nextNode; 
                }
                
                if(nextNode != -1) {
                    prev[nextNode] = prevNode; 
                    k = nextNode;
                } else {
                    k = prevNode;
                }
            } else {
                int restore = stack.pop();
                removed[restore] = false;
                
                int prevRestore = prev[restore];
                int nextRestore = next[restore];
                
                if(prevRestore != -1) 
                    next[prevRestore] = restore; 
                
                if(nextRestore != -1) 
                    prev[nextRestore] = restore; 
                
            }
        }
        
        StringBuilder answer = new StringBuilder();
        
        for(int idx = 0; idx < n; idx++) {
            if(removed[idx])
                answer.append("X"); 
            else
                answer.append("O"); 
        }
        
        return answer.toString();
    }
}