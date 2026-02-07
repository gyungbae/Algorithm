import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        for (int idx = 1; idx <= N; idx++) {
            dp[idx] = idx;

            for (int squareNum = 1; squareNum * squareNum <= idx; squareNum++) {
                dp[idx] = Math.min(dp[idx], dp[idx - squareNum * squareNum] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}
