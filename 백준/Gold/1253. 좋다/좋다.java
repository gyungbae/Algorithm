import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int answer = 0;
        loop : for (int stdIdx = 0; stdIdx < N; stdIdx++) {
            int target = arr[stdIdx];
            for (int fromIdx = 0; fromIdx < N - 1; fromIdx++) {
                if (fromIdx == stdIdx) {
                    continue;
                }

                for (int toIdx = fromIdx + 1; toIdx < N; toIdx++) {
                    if (toIdx == stdIdx) {
                        continue;
                    }

                    long sum = arr[fromIdx] + arr[toIdx];
                    if (sum == target) {
                        answer++;
                        continue loop;
                    } else if (sum > target) {
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
