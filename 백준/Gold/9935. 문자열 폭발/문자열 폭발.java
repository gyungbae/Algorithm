import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int idx = 0; idx < input.length(); idx++) {
            stack.push(input.charAt(idx));

            if (stack.size() >= bomb.length() && stack.peek() == bomb.charAt(bomb.length() - 1)) {
                boolean isBomb = true;
                for (int checkIdx = 0; checkIdx < bomb.length(); checkIdx++) {
                    if (stack.get(stack.size() - bomb.length() + checkIdx) != bomb.charAt(checkIdx)) {
                        isBomb = false;
                        break;
                    }
                }

                if (isBomb) {
                    for (int pop = 1; pop <= bomb.length(); pop++) {
                        stack.pop();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            for (char ch : stack) {
                sb.append(ch);
            }
            System.out.println(sb);
        }
    }
}
