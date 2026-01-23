import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        arr[1] = 1;
        for (int idx = 2; idx <= N; idx++) {
            arr[idx] = arr[idx - 1] + idx;
        }

        int answer = 1;
        for(int from = 1; from < N - 1; from++) {
            for (int to = from + 1; to < N; to++) {
                int sum = arr[to] - arr[from - 1];
                if (sum == N) {
                    answer++;
                }

                if (sum > N) {
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
