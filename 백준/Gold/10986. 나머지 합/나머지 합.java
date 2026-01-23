import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] sum = new long[N];
        st = new StringTokenizer(br.readLine());
        sum[0] = Integer.parseInt(st.nextToken());
        for (int idx = 1; idx < N; idx++) {
            sum[idx] = sum[idx - 1] + Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        long[] remCntArr = new long[M];
        for (int idx = 0; idx < N; idx++) {
            sum[idx] %= M;
            if (sum[idx] == 0) {
                answer++;
            }
            remCntArr[(int) sum[idx]]++;
        }

        for (long num : remCntArr) {
            answer += (num * (num - 1)) / 2;
        }

        System.out.println(answer);
    }
}
