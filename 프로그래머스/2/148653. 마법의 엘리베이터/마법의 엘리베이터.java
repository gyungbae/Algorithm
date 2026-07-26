class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0) {
            int firstDigit = storey % 10;
            int secondDigit = storey / 10 % 10;
            
            if(firstDigit < 5) {
                answer += firstDigit;
            } else if(firstDigit > 5) {
                answer += 10 - firstDigit;
                storey += 10;
            } else {
                if(secondDigit >= 5) {
                    answer += 5;
                    storey += 10;
                } else {
                    answer += 5;
                }
            }
            
            storey /= 10;
        }
        
        return answer;
    }
}
