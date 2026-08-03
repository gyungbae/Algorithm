import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        int size = bans.length;
        
        long[] banNums = new long[size];
        
        for(int i = 0; i < size; i++) {
            banNums[i] = convertString(bans[i]);
        }
        
        Arrays.sort(banNums);
        
        for(long num : banNums) {
            if(num <= n)
                n++;
            else
                break;
        }
        
        return convertNum(n);
    }
    
    private String convertNum(long num) {
        StringBuilder sb = new StringBuilder();
        
        while(num > 0) {
            num--;
            
            int quotient = (int) (num % 26);
            sb.append((char)('a' + quotient));
            
            num /= 26;
        }
        
        return sb.reverse().toString();
    }
    
    private long convertString(String num) {
        long result = 0;
        
        for(int i = 0; i < num.length(); i++) {
            int ch = num.charAt(i) - 'a' + 1;
            result = result * 26 + ch;
        }
        
        return result;
    }
}