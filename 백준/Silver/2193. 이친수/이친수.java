import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] timeArr;
    static int[] priceArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][2];
        dp[1][1] = 1;
        for (int idx = 2; idx <= N; idx++) {
            dp[idx][0] = dp[idx - 1][0] + dp[idx - 1][1];
            dp[idx][1] = dp[idx - 1][0];
        }

        System.out.println(dp[N][0] + dp[N][1]);
    }
}
