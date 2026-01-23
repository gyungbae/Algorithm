import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long answer = 0;
        for (int fromIdx = 0; fromIdx < N - 1; fromIdx++) {

            for(int toIdx = fromIdx + 1; toIdx < N; toIdx++) {
                int sum = arr[fromIdx] + arr[toIdx];
                if (sum == M) {
                    answer++;
                }

                if (sum > M) {
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
