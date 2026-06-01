import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();

        for (int idx = 0; idx < number.length(); idx++) {
            char current = number.charAt(idx);

            while (!stack.isEmpty() && k > 0 && stack.peek() < current) {
                stack.pop();
                k--;
            }

            stack.push(current);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder answer = new StringBuilder();

        for (char ch : stack) {
            answer.append(ch);
        }

        return answer.toString();
    }
}