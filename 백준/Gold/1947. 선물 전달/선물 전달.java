import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final long DIVIDE = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }

        if (N == 2) {
            System.out.println(1);
            return;
        }

        long[] dp = new long[N + 1];
        dp[1] = 0;
        dp[2] = 1;
        for (int idx = 3; idx <= N; idx++) {
            dp[idx] = ((idx - 1) * (dp[idx - 2] + dp[idx - 1])) % DIVIDE;
        }

        System.out.println(dp[N]);
    }
}
