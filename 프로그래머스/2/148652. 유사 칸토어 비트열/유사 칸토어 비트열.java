class Solution {
    boolean isOne(long num) {
        while(num > 0) {
            if(num % 5 == 2)
                return false;
        
            num /= 5;
        }
        
        return true;
    }
    
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        for(long num = l; num <= r; num++) {
            if(isOne(num - 1))
                answer++;
        }
        
        return answer;
    }
}