class Solution {
    String convert(int num, int base) {
        if(num == 0)
            return "0";
        
        String digitString = "0123456789ABCDEF";
        
        StringBuilder result = new StringBuilder();
        while(num > 0) {
            int digit = num % base;
            
            result.append(digitString.charAt(digit));
            
            num /= base;
        }
        
        return result.reverse().toString();
    }
    
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        int num = 0;
        while(sb.length() <= t * m) {
            sb.append(convert(num, n));
            num++;
        }
        
        StringBuilder answer = new StringBuilder();
        for(int idx = p - 1; t-- > 0; idx += m) {
            answer.append(sb.charAt(idx));
        }
        
        return answer.toString();
    }
}