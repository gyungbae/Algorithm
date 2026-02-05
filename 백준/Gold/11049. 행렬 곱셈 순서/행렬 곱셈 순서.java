import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] inputArr = new int[N + 1][N + 1];
        for (int input = 1; input <= N; input++) {
            String[] line = br.readLine().split(" ");
            inputArr[input][0] = Integer.parseInt(line[0]);
            inputArr[input][1] = Integer.parseInt(line[1]);
        }

        int[][] dp = new int[N + 1][N + 1];
        for (int length = 2; length <= N; length++) {
            for (int from = 1; from <= N - length + 1; from++) {
                int to = from + length - 1;

                dp[from][to] = Integer.MAX_VALUE;
                for (int mid = from; mid < to; mid++) {
                    int result = dp[from][mid] + dp[mid + 1][to] 
                            + inputArr[from][0] * inputArr[mid][1] * inputArr[to][1];
                    
                    if (result < dp[from][to]) {
                        dp[from][to] = result;
                    }
                }

            }
        }

        System.out.println(dp[1][N]);
    }
}
