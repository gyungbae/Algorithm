import java.io.*;
import java.util.*;

public class Main {
    static int K, N;
    static int[] inputArr;

    static int maxLength = 0;
    static long answer = 0;

    static void search() {
        long left = 1;
        long right = maxLength;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            long count = 0;
            for (int length : inputArr) {
                count += length / mid;
            }

            if (count < N) {
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = mid;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        inputArr = new int[K];
        for (int idx = 0; idx < K; idx++) {
            inputArr[idx] = Integer.parseInt(br.readLine());
            maxLength = Math.max(maxLength, inputArr[idx]);
        }

        search();

        System.out.println(answer);
    }
}
