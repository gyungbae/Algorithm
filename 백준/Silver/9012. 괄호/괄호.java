import java.io.*;
import java.util.*;

public class Main {
    static int T;

    static Stack<Character> stack;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String input = br.readLine();

            stack = new Stack<>();
            boolean isVPS = true;
            for (int idx = 0; idx < input.length(); idx++) {
                char ch = input.charAt(idx);
                if (ch == '(') {
                    stack.push(ch);
                } else {
                    if ( stack.isEmpty() || stack.peek() == ')') {
                        isVPS = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (!stack.isEmpty()) {
                isVPS = false;
            }
            
            sb.append(isVPS ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }
}
