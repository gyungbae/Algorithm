import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (n <= 2) {
                sb.append(n).append("\n");
                continue;
            }

            if (n == 3) {
                sb.append(4).append("\n");
                continue;
            }

            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            for (int idx = 4; idx <= n; idx++) {
                dp[idx] = dp[idx - 1] + dp[idx - 2] + dp[idx - 3];
            }

            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb);
    }
}
