import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][10];
        Arrays.fill(dp[1], 1, 10, 1);

        for (int idx = 2; idx <= N; idx++) {
            for (int endNum = 0; endNum <= 9; endNum++) {
                if (endNum == 0) {
                    dp[idx][endNum] = dp[idx - 1][1];
                    continue;
                }

                if (endNum == 9) {
                    dp[idx][endNum] = dp[idx - 1][8];
                    continue;
                }

                dp[idx][endNum] = (dp[idx - 1][endNum - 1] + dp[idx - 1][endNum + 1]) % MOD;
            }
        }

        long answer = 0;
        for (int value : dp[N]) {
            answer = (answer + value) % MOD;
        }
        
        System.out.println(answer);
    }
}
