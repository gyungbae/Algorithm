import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int pushNum = 1;
        for (int input = 1; input <= n; input++) {
            int num = Integer.parseInt(br.readLine());
            while (pushNum <= num) {
                stk.push(pushNum);
                sb.append("+").append("\n");
                pushNum++;
            }

            if (num > stk.peek()) {
                System.out.println("NO");
                return;
            }

            stk.pop();
            sb.append("-").append("\n");

        }

        System.out.println(sb);
    }
}
