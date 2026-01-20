import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] weightArr;
    static int[] valueArr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        weightArr = new int[N + 1];
        valueArr = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            st = new StringTokenizer(br.readLine());
            weightArr[idx] = Integer.parseInt(st.nextToken());
            valueArr[idx] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];
        for (int idx = 1; idx <= N; idx++) {
            for (int weight = 0; weight <= K; weight++) {
                dp[idx][weight] = dp[idx - 1][weight];

                if (weight >= weightArr[idx]) {
                    dp[idx][weight] = Math.max(dp[idx][weight], dp[idx - 1][weight - weightArr[idx]] + valueArr[idx]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
