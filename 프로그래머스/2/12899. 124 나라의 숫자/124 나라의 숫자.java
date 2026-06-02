class Solution {
    public String solution(int n) {
       StringBuilder sb = new StringBuilder();
        
        while(n > 0) {
            int remainder = n % 3;
            n /= 3;
            
            if(remainder == 0) {
                sb.append("4");
                n--;
            } else if(remainder == 1) {
                sb.append("1");
            } else {
                sb.append("2");
            }
        }
        
        return sb.reverse().toString();
    }
}