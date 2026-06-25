import java.util.*;

class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] dp = new HashSet[9];
        
        for(int count = 1; count <= 8; count++) {
            dp[count] = new HashSet<>();
        }
        
        for(int count = 1; count <= 8; count++) {
            int num = 0;
            
            for(int repeat = 0; repeat < count; repeat++) {
                num = num * 10 + N;
            }
            
            dp[count].add(num);
            
           for (int leftCount = 1; leftCount < count; leftCount++) {
                int rightCount = count - leftCount;

                for (int leftValue : dp[leftCount]) {
                    for (int rightValue : dp[rightCount]) {
                        dp[count].add(leftValue + rightValue);
                        dp[count].add(leftValue - rightValue);
                        dp[count].add(leftValue * rightValue);

                        if (rightValue != 0) {
                            dp[count].add(leftValue / rightValue);
                        }
                    }
                }
            }

            if (dp[count].contains(number)) {
                return count;
            }
        }

        return -1;
    }
}