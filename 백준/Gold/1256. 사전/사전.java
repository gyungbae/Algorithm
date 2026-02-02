import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        int[][] dp = new int[N + 1][M + 1];
        for (int aCount = 0; aCount <= N; aCount++) {
            for (int zCount = 0; zCount <= M; zCount++) {
                if (aCount == 0 || zCount == 0) {
                    dp[aCount][zCount] = 1;
                    continue;
                }

                dp[aCount][zCount] = Math.min(dp[aCount - 1][zCount] + dp[aCount][zCount - 1], K);
            }
        }

        if (dp[N][M] < K) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (N > 0 && M > 0) {
            int length = dp[N - 1][M];

            if (K <= length) {
                sb.append("a");
                N--;
            } else {
                sb.append("z");
                K -= length;
                M--;
            }
        }

        while (N > 0) {
            sb.append("a");
            N--;
        }

        while (M > 0) {
            sb.append("z");
            M--;
        }

        System.out.println(sb);
    }
}
