import java.util.*;

class Solution {
    int[] cards;
    int size;
    
    boolean[] opened;
    
    int open(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        opened[start] = true;
        queue.offer(start);
        
        int count = 0;
        while(!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            int next = cards[current] - 1;
            
            if(!opened[next]) {
                opened[next] = true;
                queue.offer(next);
            }
        }
        
        return count;
    }
    
    public int solution(int[] cards) {
        this.cards = cards;
        size = cards.length;
        
        opened = new boolean[size];
        
        int first = 0;
        int second = 0;
        
        for(int boxIdx = 0; boxIdx < size; boxIdx++) {
            if(opened[boxIdx])
                continue;
            
            int count = open(boxIdx);
            
            if(first < count) {
                second = first;
                first = count;
            } else if(second < count) {
                second = count;
            }
        }
        
        return first * second;
    }
}