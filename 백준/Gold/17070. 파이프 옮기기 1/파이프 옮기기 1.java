import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] inputArr;

    static int[][][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        inputArr = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                inputArr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][N][3];
        dp[0][1][0] = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (inputArr[row][col] == 1) {
                    continue;
                }

                if (col - 1 >= 0) {
                    dp[row][col][0] += dp[row][col - 1][0] + dp[row][col - 1][2];
                }

                if (row - 1 >= 0) {
                    dp[row][col][1] += dp[row - 1][col][1] + dp[row - 1][col][2];
                }

                if (row - 1 >= 0 && col - 1 >= 0 && inputArr[row - 1][col] == 0 && inputArr[row][col - 1] == 0) {
                    dp[row][col][2] += dp[row - 1][col - 1][0] + dp[row - 1][col - 1][1] + dp[row - 1][col - 1][2];
                }
            }
        }

        int answer = 0;
        for (int value : dp[N - 1][N - 1]) {
            answer += value;
        }

        System.out.println(answer);
    }
}
