import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int k;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            k = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());

            int[][] dp = new int[k + 1][n + 1];
            for (int room = 1; room <= n; room++) {
                dp[0][room] = room;
            }

            for (int floor = 1; floor <= k; floor++) {
                dp[floor][1] = 1;
            }

            for (int floor = 1; floor <= k; floor++) {
                for (int room = 2; room <= n; room++) {
                    dp[floor][room] = dp[floor - 1][room] + dp[floor][room - 1];
                }
            }

            sb.append(dp[k][n]).append("\n");
        }

        System.out.println(sb);
    }
}
