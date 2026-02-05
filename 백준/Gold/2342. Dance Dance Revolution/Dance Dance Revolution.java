import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> commandList = new ArrayList<>();

    static int move(int from, int to) {
        if (from == to) {
            return 1;
        }

        if (from == 0) {
            return 2;
        }

        if (Math.abs(from - to) == 2) {
            return 4;
        }

        return 3;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            commandList.add(Integer.parseInt(st.nextToken()));
        }

        int commandCount = commandList.size() - 1;
        int[][][] dp = new int[commandCount + 1][5][5];
        for (int commandIdx = 0; commandIdx <= commandCount; commandIdx++) {
            for (int left = 0; left <= 4; left++) {
                for (int right = 0; right <= 4; right++) {
                    dp[commandIdx][left][right] = Integer.MAX_VALUE;
                }
            }
        }
        dp[0][0][0] = 0;

        for (int commandIdx = 1; commandIdx <= commandCount; commandIdx++) {
            int command = commandList.get(commandIdx - 1);
            for (int left = 0; left <= 4; left++) {
                for (int right = 0; right <= 4; right++) {
                    int previous = dp[commandIdx - 1][left][right];
                    if (previous == Integer.MAX_VALUE) {
                        continue;
                    }

                    dp[commandIdx][command][right] = Math.min(dp[commandIdx][command][right],
                            previous + move(left, command));
                    dp[commandIdx][left][command] = Math.min(dp[commandIdx][left][command],
                            previous + move(right, command));

                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int left = 0; left <= 4; left++) {
            for (int right = 0; right <= 4; right++) {
                answer = Math.min(dp[commandCount][left][right], answer);
            }
        }

        System.out.println(answer);
    }
}
