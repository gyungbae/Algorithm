import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            numArr[idx] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N + 1];
        dp[1] = numArr[1];
        if (N == 1) {
            System.out.println(dp[1]);
            return;
        }

        dp[2] = numArr[1] + numArr[2];
        if (N == 2) {
            System.out.println(dp[N]);
            return;
        }

        dp[3] = Math.max(numArr[1], numArr[2]) + numArr[3];
        if (N == 3) {
            System.out.println(dp[3]);
            return;
        }

        for (int idx = 4; idx <= N; idx++) {
            dp[idx] = Math.max(dp[idx - 2], dp[idx - 3] + numArr[idx - 1]) + numArr[idx];
        }

        System.out.println(dp[N]);
    }
}
