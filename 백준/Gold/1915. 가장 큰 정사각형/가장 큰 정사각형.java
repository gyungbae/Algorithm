import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] inputArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        inputArr = new int[n + 1][m + 1];
        for (int row = 1; row <= n; row++) {
            String line = br.readLine();
            for (int col = 1; col <= m; col++) {
                inputArr[row][col] = line.charAt(col - 1) - '0';
            }
        }

        int[][] dp = new int[n + 1][m + 1];
        int answer = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                if (inputArr[row][col] == 1) {
                    dp[row][col] = Math.min(dp[row - 1][col - 1], Math.min(dp[row][col - 1], dp[row - 1][col])) + 1;
                }

                answer = Math.max(answer, dp[row][col]);
            }
        }

        System.out.println(answer * answer);
    }
}
