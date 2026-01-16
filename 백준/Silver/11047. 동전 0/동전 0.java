import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        int idx = N - 1;
        while (K > 0) {
            if (arr[idx] > K) {
                idx--;
                continue;
            }

            answer += K / arr[idx];
            K %= arr[idx];
            idx--;
        }

        System.out.println(answer);
    }
}
