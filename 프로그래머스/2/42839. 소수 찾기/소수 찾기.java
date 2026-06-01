import java.util.*;

class Solution {
    char[] numArr;
    boolean[] selected;
    Set<Integer> set = new HashSet<>();
    
    boolean isPrime(int num) {
        if(num < 2)
            return false;
        
        for(int divisor = 2; divisor <= Math.sqrt(num); divisor++) {
            if(num % divisor == 0)
                return false;
        }
        
        return true;
    }
    
    void search(String current) {
        if(!current.equals("")) {
            int num = Integer.parseInt(current);
            
            if(isPrime(num))
                set.add(num);
        }
        
        for(int idx = 0; idx < numArr.length; idx++) {
            if(selected[idx])
                continue;
            
            selected[idx] = true;
            search(current + numArr[idx]);
            selected[idx] = false;
        }
    }
    
    public int solution(String numbers) {
        numArr = numbers.toCharArray();
        selected = new boolean[numArr.length];
        
        search("");
        
        return set.size();
    }
}