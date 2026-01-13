import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int n;
    static int[][] input;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            input = new int[2][n + 1];
            for (int row = 0; row < 2; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int col = 1; col <= n; col++) {
                    input[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[n + 1][3];
            dp[1][1] = input[0][1];
            dp[1][2] = input[1][1];
            for (int idx = 2; idx <= n; idx++) {
                dp[idx][0] = Math.max(Math.max(dp[idx-1][0], dp[idx-1][1]), dp[idx-1][2]);
                dp[idx][1] = Math.max(dp[idx-1][0], dp[idx-1][2]) + input[0][idx];
                dp[idx][2] = Math.max(dp[idx-1][0], dp[idx-1][1]) + input[1][idx];
            }

            int answer = 0;
            for (int val : dp[n]) {
                answer = Math.max(answer, val);
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
