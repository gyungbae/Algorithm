import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            dp[row][1] = row;
            dp[row][0] = 1;
            dp[row][row] = 1;
        }

        for (int row = 2; row <= N; row++) {
            for (int col = 1; col < row; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row - 1][col - 1];
            }
        }

        System.out.println(dp[N][K]);
    }
}
