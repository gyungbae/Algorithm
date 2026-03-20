import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int score = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] list = new int[N];

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }
        }

        if (N == 0) {
            System.out.println(1);
            return;
        }

        if (N == P && list[N - 1] >= score) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (list[i] <= score) {
                System.out.println(i + 1);
                return;
            }
        }

        System.out.println(N + 1);
    }
}
