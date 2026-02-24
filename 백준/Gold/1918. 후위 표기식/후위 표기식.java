import java.io.*;
import java.util.*;

public class Main {
    static int getPriority(char operator) {
        if(operator == '*' || operator == '/')
            return 2;

        return 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < input.length(); idx++) {
            char character = input.charAt(idx);
            if ('A' <= character && character <= 'Z') {
                sb.append(character);
                continue;
            }

            if (character == '(') {
                stack.push(character);
                continue;
            }

            if (character == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
                continue;
            }

            while (!stack.isEmpty() && stack.peek() != '(' && getPriority(stack.peek()) >= getPriority(character)) {
                sb.append(stack.pop());
            }
            stack.push(character);
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
