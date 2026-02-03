import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] timeArr;
    static int[] priceArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        timeArr = new int[N + 1];
        priceArr = new int[N + 1];
        for (int input = 1; input <= N; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            timeArr[input] = Integer.parseInt(st.nextToken());
            priceArr[input] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 2];
        for (int idx = 1; idx <= N; idx++) {
            dp[idx + 1] = Math.max(dp[idx+ 1], dp[idx]);

            int endDay = idx + timeArr[idx];
            if (endDay <= N + 1) {
                dp[endDay] = Math.max(dp[endDay], dp[idx] + priceArr[idx]);
            }
        }

        System.out.println(dp[N + 1]);
    }
}
