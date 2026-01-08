import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N + 1][3];
        for (int house = 1; house <= N; house++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[house][0] = Integer.parseInt(st.nextToken());
            arr[house][1] = Integer.parseInt(st.nextToken());
            arr[house][2] = Integer.parseInt(st.nextToken());
        }

        //i번째 집에 R, G, B를 칠했을 때의 최소 비용
        int[][] dp = new int[N + 1][3];
        dp[1][0] = arr[1][0];
        dp[1][1] = arr[1][1];
        dp[1][2] = arr[1][2];

        for (int house = 2; house <= N; house++) {
            dp[house][0] = arr[house][0] + Math.min(dp[house - 1][1], dp[house - 1][2]);
            dp[house][1] = arr[house][1] + Math.min(dp[house - 1][0], dp[house - 1][2]);
            dp[house][2] = arr[house][2] + Math.min(dp[house - 1][0], dp[house - 1][1]);
        }

        int answer = Integer.MAX_VALUE;
        for(int val : dp[N]) {
            answer = Math.min(answer, val);
        }

        System.out.println(answer);
    }
}
