import java.io.*;
import java.util.*;

public class Main {
    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        int[][] dp = new int[41][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;
        for (int idx = 2; idx <= 40; idx++) {
            dp[idx][0] = dp[idx - 1][0] + dp[idx - 2][0];
            dp[idx][1] = dp[idx - 1][1] + dp[idx - 2][1];
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
        }

        System.out.println(sb);
    }
}
