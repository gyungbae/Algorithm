import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static String p;
    static int n;
    static Deque<Integer> dq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            p = br.readLine();
            n = Integer.parseInt(br.readLine());
            dq = new ArrayDeque<>();
            String input = br.readLine();
            String[] arr = input.substring(1, input.length() - 1).split(",");
            for (String str : arr) {
                if (str.equals("")) {
                    break;
                }
                dq.offer(Integer.parseInt(str));
            }

            boolean reversed = false;
            boolean isError = false;
            for (int idx = 0; idx < p.length(); idx++) {
                char order = p.charAt(idx);
                if (order == 'R') {
                    reversed = !reversed;
                } else {
                    if (dq.isEmpty()) {
                        isError = true;
                        break;
                    }

                    if (reversed) {
                        dq.pollLast();
                    } else {
                        dq.poll();
                    }
                }
            }

            if (isError) {
                sb.append("error").append("\n");
                continue;
            }

            sb.append("[");
            while (!dq.isEmpty()) {
                sb.append(reversed ? dq.pollLast() : dq.poll());
                if (!dq.isEmpty()) {
                    sb.append(",");
                }
            }
            sb.append("]").append("\n");
        }

        System.out.println(sb);
    }
}
