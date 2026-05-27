class Solution {
    long search(long num) {
        long divisor = 1;
        while(divisor * 2 <= num) {
            divisor *= 2;
        }
        
        String result = "";
        while(divisor > 0) {
            if(num >= divisor) {
                num -= divisor;
                result += "1";
            } else {
                result += "0";
            }
            
            divisor /= 2;
        }
        
        int lastZero = 0;
        for(int idx = result.length() - 1; idx >= 0; idx--) {
            if(result.charAt(idx) == '0') {
                lastZero = idx + 1;
                break;
            }
        }
        
        if(lastZero == -1)
            return 2;
        
        return (long) Math.pow(2, result.length() - lastZero - 1);
    }
    
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int idx = 0; idx < numbers.length; idx++) {
            long number = numbers[idx];
            
            if(number % 2 == 0) {
                answer[idx] = number + 1;
            } else {
                answer[idx] = number + search(number);
            }
        }
        
        return answer;
    }
}