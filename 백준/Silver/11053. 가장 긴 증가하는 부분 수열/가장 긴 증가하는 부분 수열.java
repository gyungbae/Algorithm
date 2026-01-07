import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            numArr[idx] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int answer = 0;
        for (int idx = 0; idx < N; idx++) {
            for (int cmpIdx = 0; cmpIdx < idx; cmpIdx++) {
                if (numArr[cmpIdx] < numArr[idx]) {
                    dp[idx] = Math.max(dp[idx], dp[cmpIdx] + 1);
                }
            }

            answer = Math.max(answer, dp[idx]);
        }

        System.out.println(answer);
    }
}
