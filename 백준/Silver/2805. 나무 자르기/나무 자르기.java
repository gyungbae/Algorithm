import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;

    static int answer;

    static void search(int height) {
        int left = 0;
        int right = height;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sum = 0;
            for (int val : arr) {
                if (val > mid) {
                    sum += val - mid;
                }
            }

            if (sum >= M) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int idx = 0; idx < N; idx++) {
            int val = Integer.parseInt(st.nextToken());
            arr[idx] = val;
            max = Math.max(max, val);
        }

        search(max);
        System.out.println(answer);
    }
}
