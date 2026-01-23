import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int answer = 0;
        for (int stdIdx = 0; stdIdx < N; stdIdx++) {
            int target = arr[stdIdx];
            int leftIdx = 0, rightIdx = N - 1;

            while (leftIdx < rightIdx) {
                if (leftIdx == stdIdx) {
                    leftIdx++;
                    continue;
                }

                if (rightIdx == stdIdx) {
                    rightIdx--;
                    continue;
                }

                long sum = (long) arr[leftIdx] + arr[rightIdx];

                if (sum == target) {
                    answer++;
                    break; 
                } else if (sum < target) {
                    leftIdx++;
                } else {
                    rightIdx--;
                }
            }
        }

        System.out.println(answer);
    }
}
