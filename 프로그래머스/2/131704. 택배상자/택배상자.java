import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        for (int num = 1; num <= order.length; num++) {
            queue.offer(num);
        }

        Stack<Integer> stack = new Stack<>();

        for (int num : order) {
            while (!queue.isEmpty() && queue.peek() < num) {
                stack.push(queue.poll());
            }

            if (!queue.isEmpty() && queue.peek() == num) {
                queue.poll();
                answer++;
                continue;
            }

            if (!stack.isEmpty() && stack.peek() == num) {
                stack.pop();
                answer++;
                continue;
            }

            break;
        }

        return answer;
    }
}