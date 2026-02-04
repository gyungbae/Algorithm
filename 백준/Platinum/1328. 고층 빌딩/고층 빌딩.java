import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);
        int R = Integer.parseInt(input[2]);

        final int MOD = 1000000007;

        long[][][] dp = new long[N + 1][L + 1][R + 1];
        dp[1][1][1] = 1;
        for (int idx = 2; idx <= N; idx++) {
            for (int left = 1; left <= L; left++) {
                for (int right = 1; right <= R; right++) {
                    dp[idx][left][right] = (dp[idx-1][left][right] * (idx - 2) +
                            dp[idx - 1][left - 1][right] + dp[idx - 1][left][right - 1]) % MOD;
                }
            }
        }

        System.out.println(dp[N][L][R]);
    }
}
