class Solution {
    public int[] solution(int n, long k) {
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for(int idx = 1; idx <= n; idx++) {
            factorial[idx] = factorial[idx - 1] * idx;
        }
        
        boolean[] selected = new boolean[n + 1];
        int[] answer = new int[n];
        
        k--;
        
        int factorialIdx = n - 1;
        int answerIdx = 0;
        
        while(answerIdx < n) {
            int quotient = (int) (k / factorial[factorialIdx]);
            
            int order = 0;
            
            for (int number = 1; number <= n; number++) {
                if (selected[number]) {
                    continue;
                }

                if (order == quotient) {
                    answer[answerIdx++] = number;
                    selected[number] = true;
                    break;
                }

                order++;
            }
            
            k %= factorial[factorialIdx--];
        }
        
        return answer;
    }
}