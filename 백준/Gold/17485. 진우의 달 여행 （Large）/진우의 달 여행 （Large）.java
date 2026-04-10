import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][M][3];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (row == 0) {
                    Arrays.fill(dp[row][col], map[row][col]);
                } else {
                    Arrays.fill(dp[row][col], Integer.MAX_VALUE);
                }
            }
        }

        for (int row = 1; row < N; row++) {
            int prev = row - 1;

            for (int col = 0; col < M; col++) {
                int left = col - 1;
                int right = col + 1;

                if(right < M) {
                    dp[row][col][0] = Math.min(dp[prev][right][1], dp[prev][right][2]);
                }

                dp[row][col][1] = Math.min(dp[prev][col][0], dp[prev][col][2]);

                if (left >= 0) {
                    dp[row][col][2] = Math.min(dp[prev][left][0], dp[prev][left][1]);
                }

                for (int direction = 0; direction <= 2; direction++) {
                    if(dp[row][col][direction] == Integer.MAX_VALUE)
                        continue;

                    dp[row][col][direction] += map[row][col];
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int col = 0; col < M; col++) {
            for (int direction = 0; direction <= 2; direction++) {
                answer = Math.min(answer, dp[N - 1][col][direction]);
            }
        }

        System.out.println(answer);
    }
}
