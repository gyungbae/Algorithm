import java.util.*;

class Solution {
    String numbers;
    int length;
    
    Set<Integer> set = new HashSet<>();
    boolean[] selected;
    
    void search(int depth, String word) {
        
        if(!word.equals("") && isPrime(word)) {
            int num = Integer.parseInt(word);
            set.add(num);
        }
        
        if(depth == length) {
            return;
        }
        
        for(int idx = 0; idx < length; idx++) {
            if(selected[idx])
                continue;
            
            selected[idx] = true;
            search(depth + 1, word + numbers.charAt(idx));
            selected[idx] = false;
        }
    }
    
    boolean isPrime(String word) {
        int num = Integer.parseInt(word);
        
        if(num <= 1)
            return false;
        
        for(int divisor = 2; divisor <= Math.sqrt(num); divisor++) {
            if(num % divisor == 0)
                return false;
        }
        
        return true;
    }
    
    public int solution(String numbers) {
        this.numbers = numbers;
        length = numbers.length();
        
        selected = new boolean[length];
        search(0, "");
        
        return set.size();
    }
}