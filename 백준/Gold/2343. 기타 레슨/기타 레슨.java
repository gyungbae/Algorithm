import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] numArr;

    static int max;
    static int sum;
    static int answer;

    static void binarySearch() {
        int left = max;
        int right = sum;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int count = 0;
            int idx = 0;
            while (idx < N) {
                int size = mid;
                while (numArr[idx] <= size) {
                    size -= numArr[idx];
                    idx++;
                    if (idx == N) {
                        break;
                    }
                }
                count++;
            }

            if (count > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numArr = new int[N];
        max = 0;
        sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            int num = Integer.parseInt(st.nextToken());
            numArr[idx] = num;
            sum += num;
            max = Math.max(max, num);
        }

        binarySearch();

        System.out.println(answer);
    }
}
