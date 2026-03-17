import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] info = new int[N][2];

        for (int idx = 0; idx < N; idx++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            info[idx][0] = Integer.parseInt(st.nextToken()); // 몸무게
            info[idx][1] = Integer.parseInt(st.nextToken()); // 키
        }

        StringBuilder sb = new StringBuilder();

        for (int idx1 = 0; idx1 < N; idx1++) {
            int rank = 1;

            for (int idx2 = 0; idx2 < N; idx2++) {
                if (idx1 == idx2) continue;

                if (info[idx2][0] > info[idx1][0] && info[idx2][1] > info[idx1][1]) {
                    rank++;
                }
            }

            sb.append(rank).append(" ");
        }

        System.out.println(sb);
    }
}
