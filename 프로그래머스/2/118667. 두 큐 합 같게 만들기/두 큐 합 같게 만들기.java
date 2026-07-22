import java.util.*; 

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        int max = queue1.length * 4;

        long sum1 = 0;
        for (int num : queue1) {
            sum1 += num;
            q1.offer(num);
        }

        long sum2 = 0;
        for (int num : queue2) {
            sum2 += num;
            q2.offer(num);
        }

        if ((sum1 + sum2) % 2 != 0)
            return -1;

        int count = 0;

        while (count < max) { 
            if (sum1 > sum2) {
                int num = q1.poll();

                sum1 -= num; 
                sum2 += num;

                q2.offer(num);
                count++;
            } else if (sum2 > sum1) { 
                int num = q2.poll();

                sum2 -= num; 
                sum1 += num;

                q1.offer(num);
                count++;
            } else {
                return count; 
            }
        }

        return -1; 
    }
}
