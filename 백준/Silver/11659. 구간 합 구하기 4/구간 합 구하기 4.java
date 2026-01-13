import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] input;

    static int[] sum;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        sum[1] = Integer.parseInt(st.nextToken());
        for (int idx = 2; idx <= N; idx++) {
            sum[idx] = sum[idx - 1] + Integer.parseInt(st.nextToken());
        }

        for (int line = 1; line <= M; line++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            sb.append(sum[to] - sum[from - 1]).append("\n");
        }

        System.out.println(sb);
    }
}
