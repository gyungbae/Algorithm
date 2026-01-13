import java.io.*;
import java.util.*;

public class Main {
    static int N;

    static int[] dp;

    static void DFS(int curNum, int depth) {
        if (curNum <= 0) {
            return;
        }

        if (depth >= dp[curNum]) {
            return;
        }

        dp[curNum] = depth;

        if (curNum % 3 == 0) {
            DFS(curNum / 3, depth + 1);
        }

        if (curNum % 2 == 0) {
            DFS(curNum / 2, depth + 1);
        }

        DFS(curNum - 1, depth + 1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        DFS(N, 0);

        System.out.println(dp[1]);
    }
}
