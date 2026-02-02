import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        long[][] dp = new long[31][31];
        for (int row = 1; row <= 30; row++) {
            dp[row][0] = 1;
            dp[row][1] = row;
            dp[row][row] = 1;
        }

        for (int row = 2; row <= 30; row++) {
            for (int col = 1; col < row; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row - 1][col - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            sb.append(dp[M][N]).append("\n");
        }

        System.out.println(sb);
    }
}
