class Solution {
    public long solution(int[] sequence) {
        long answer = Long.MIN_VALUE;
        
        long sum1 = 0;
        long sum2 = 0;
        
        long pulse1 = 1;
        long pulse2 = -1;
        
        for(int num : sequence) {
            long value1 = num * pulse1;
            long value2 = num * pulse2;
            
            sum1 = Math.max(value1, sum1 + value1);
            sum2 = Math.max(value2, sum2 + value2);
            
            answer = Math.max(answer, Math.max(sum1, sum2));
            
            pulse1 *= -1;
            pulse2 *= -1;
        }
        
        return answer;
    }
}