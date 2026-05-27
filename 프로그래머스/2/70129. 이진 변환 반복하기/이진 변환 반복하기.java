class Solution {
    int[] answer;
    
    void convert(String current, int depth, int zero) {
        if(current.equals("1")) {
            answer[0] = depth;
            answer[1] = zero;
            return;
        }
        
        String tmpNext = "";
        for(int idx = 0; idx < current.length(); idx++) {
            if(current.charAt(idx) == '1')
                tmpNext += "1";
        }
        
        int tmpLength = tmpNext.length();
        int nextLength = tmpLength;
        
        int divisor = 1;
        while(divisor * 2 <= tmpLength) {
            divisor *= 2;
        }
        
        String next = "";
        while(divisor > 0) {
            if(nextLength >= divisor) {
                nextLength -= divisor;
                next += "1";
            } else {
                next += "0";
            }
            
            divisor /= 2;
        }
        
        convert(next, depth + 1, zero + current.length() - tmpNext.length());
    }
    
    public int[] solution(String s) {
        answer = new int[2];
        convert(s, 0, 0);
        return answer;
    }
}