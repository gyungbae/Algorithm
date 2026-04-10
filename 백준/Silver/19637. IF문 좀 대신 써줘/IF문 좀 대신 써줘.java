import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] nameArr = new String[N];
        int[] powerArr = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nameArr[i] = st.nextToken();
            powerArr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(br.readLine());

            int left = 0;
            int right = N - 1;
            int idx = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (powerArr[mid] >= target) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            sb.append(nameArr[idx]).append("\n");
        }

        System.out.println(sb);
    }
}
