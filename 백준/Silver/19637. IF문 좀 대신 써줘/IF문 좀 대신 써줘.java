import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] name = new String[N];
        int[] power = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            name[i] = st.nextToken();
            power[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(br.readLine());

            int left = 0;
            int right = N - 1;
            int answer = N - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (power[mid] >= target) {
                    answer = mid;
                    right = mid - 1;   
                } else {
                    left = mid + 1;
                }
            }

            sb.append(name[answer]).append('\n');
        }

        System.out.print(sb);
    }
}
