import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        int[] previous = new int[N + 1];

        for(int num = 2; num <= N; num++) {
            dp[num] = dp[num - 1] + 1;
            previous[num] = num - 1;

            if(num % 2 == 0 && dp[num / 2] + 1 < dp[num]) {
                dp[num] = dp[num / 2] + 1;
                previous[num] = num / 2;
            }

            if(num % 3 == 0 && dp[num / 3] + 1 < dp[num]) {
                dp[num] = dp[num / 3] + 1;
                previous[num] = num / 3;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[N]).append("\n");

        int current = N;
        while(current != 0) {
            sb.append(current).append(" ");
            current = previous[current];
        }

        System.out.println(sb);
    }
}
