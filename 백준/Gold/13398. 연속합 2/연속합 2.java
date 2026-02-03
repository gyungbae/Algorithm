import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] inputArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        inputArr = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            inputArr[idx] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][2];
        dp[1][0] = inputArr[1];
        dp[1][1] = Math.max(0, inputArr[1]);

        int answer = dp[1][0];
        for (int idx = 2; idx <= N; idx++) {
            int num = inputArr[idx];
            dp[idx][0] = Math.max(dp[idx - 1][0] + num, num);
            dp[idx][1] = Math.max(dp[idx - 1][0], dp[idx - 1][1] + num);
            answer = Math.max(answer, Math.max(dp[idx][0], dp[idx][1]));
        }

        System.out.println(answer);
    }
}
