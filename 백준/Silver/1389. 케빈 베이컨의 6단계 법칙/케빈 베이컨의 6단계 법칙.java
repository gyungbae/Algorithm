import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] friend = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            Arrays.fill(friend[row], Integer.MAX_VALUE);
            friend[row][row] = 0;
        }

        for (int edge = 1; edge <= M; edge++) {
            st = new StringTokenizer(br.readLine());
            int person1 = Integer.parseInt(st.nextToken());
            int person2 = Integer.parseInt(st.nextToken());

            friend[person1][person2] = 1;
            friend[person2][person1] = 1;
        }

        for (int via = 1; via <= N; via++) {
            for (int from = 1; from <= N; from++) {
                if(friend[from][via] == Integer.MAX_VALUE) continue;
                for (int to = 1; to <= N; to++) {
                    if (friend[via][to] != Integer.MAX_VALUE) {
                        friend[from][to] = Math.min(friend[from][via] + friend[via][to], friend[from][to]);
                    }
                }
            }
        }

        int minSum = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int person = 1; person <= N; person++) {
            int sum = 0;
            for (int num : friend[person]) {
                sum += num;
            }

            if (sum < minSum) {
                minIdx = person;
                minSum = sum;
            }
        }

        System.out.println(minIdx);
    }
}
