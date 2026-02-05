import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] adjMatrix;

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                adjMatrix[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int maxVisit = 1 << N;
        dp = new int[N][maxVisit];
        for (int idx = 0; idx < N; idx++) {
            Arrays.fill(dp[idx], Integer.MAX_VALUE);
        }

        dp[0][1] = 0;

        for (int mask = 0; mask < maxVisit; mask++) {
            for (int from = 0; from < N; from++) {
                if ((mask & (1 << from)) == 0) {
                    continue;
                }

                if (dp[from][mask] == Integer.MAX_VALUE) {
                    continue;
                }

                for (int to = 0; to < N; to++) {
                    if ((mask & (1 << to)) != 0) {
                        continue;
                    }

                    if (adjMatrix[from][to] == 0) {
                        continue;
                    }

                    int nextMask = mask | (1 << to);
                    dp[to][nextMask] = Math.min(dp[to][nextMask], dp[from][mask] + adjMatrix[from][to]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int idx = 0; idx < N; idx++) {
            if (dp[idx][maxVisit - 1] == Integer.MAX_VALUE) {
                continue;
            }

            if (adjMatrix[idx][0] == 0) {
                continue;
            }

            answer = Math.min(answer, dp[idx][maxVisit - 1] + adjMatrix[idx][0]);
        }

        System.out.println(answer);
    }
}
