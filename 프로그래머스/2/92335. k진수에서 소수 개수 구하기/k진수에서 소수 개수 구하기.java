import java.util.*;

class Solution {
    String convert(int num, int digit) {
        String result = "";
        
        int divisor = 1;
        while(divisor * digit <= num) {
            divisor *= digit;
        }
        
        while(divisor > 0) {
            int quotient = num / divisor; 
            result += quotient;
            num -= quotient * divisor;
            divisor /= digit;
        }
        
        return result;
    }
    
    boolean isPrime(long num) {
        if(num == 1)
            return false;
        
        for(int divisor = 2; divisor <= Math.sqrt(num); divisor++) {
            if(num % divisor == 0)
                return false;
        }
        
        return true;
    }
    
    public int solution(int n, int k) {
        int answer = 0;
        
        String str = convert(n, k);
        String[] arr = str.split("0");

        for(String num : arr) {
            if(num.equals(""))
                continue;
            
            if(isPrime(Long.parseLong(num)))
                answer++;
        }
        
        return answer;
    }
}