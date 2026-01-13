import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] input;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        input = new int[n][n];
        for (int row = 0; row < n; row++) {
            Arrays.fill(input[row], -1);
        }

        for (int row = 0; row < n; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                if (st.hasMoreTokens()) {
                    input[row][col] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int[][] dp = new int[n][n];
        dp[0][0] = input[0][0];
        for (int row = 1; row < n; row++) {
            for (int col = 0; col < row + 1; col++) {
                if (col == 0) {
                    dp[row][col] = dp[row - 1][col] + input[row][col];
                    continue;
                }

                if (col == row) {
                    dp[row][col] = dp[row - 1][col - 1] + input[row][col];
                    continue;
                }

                dp[row][col] = Math.max(dp[row - 1][col - 1], dp[row - 1][col]) + input[row][col];
            }
        }

        int answer = 0;
        for (int sum : dp[n - 1]) {
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
