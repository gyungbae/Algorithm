import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++)
            input[idx] = Integer.parseInt(st.nextToken());

        int[] ascendingDp = new int[N];
        Arrays.fill(ascendingDp, 1);
        for (int standardIdx = 0; standardIdx < N; standardIdx++) {
            for (int compareIdx = 0; compareIdx < standardIdx; compareIdx++) {
                if(input[compareIdx] < input[standardIdx])
                    ascendingDp[standardIdx] = Math.max(ascendingDp[standardIdx], ascendingDp[compareIdx] + 1);
            }
        }

        int[] descendingDp = new int[N];
        Arrays.fill(descendingDp, 1);
        for (int standardIdx = N - 1; standardIdx >= 0; standardIdx--) {
            for (int compareIdx = N - 1; compareIdx > standardIdx; compareIdx--) {
                if (input[compareIdx] < input[standardIdx])
                    descendingDp[standardIdx] = Math.max(descendingDp[standardIdx], descendingDp[compareIdx] + 1);
            }
        }

        int answer = 0;
        for (int idx = 0; idx < N; idx++)
            answer = Math.max(answer, ascendingDp[idx] + descendingDp[idx] - 1);

        System.out.println(answer);
    }
}
