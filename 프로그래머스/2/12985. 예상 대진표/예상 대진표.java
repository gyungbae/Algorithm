class Solution {
    public int solution(int n, int a, int b) {
        int answer = 0;
        while(true) {
            if(a == b)
                break;
            
            answer++;
            
            if(a % 2 != 0)
                a++;
            
            if(b % 2 != 0)
                b++;
            
            a /= 2;
            b /= 2;
        }
        
        return answer;
    }
}