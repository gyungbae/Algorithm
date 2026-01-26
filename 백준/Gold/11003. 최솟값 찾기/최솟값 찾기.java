import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < N; idx++) {
            while (!queue.isEmpty() && queue.peek() <= idx - L) {
                queue.poll();
            }

            while (!queue.isEmpty() && arr[queue.peekLast()] >= arr[idx]) {
                queue.pollLast();
            }

            queue.offer(idx);

            sb.append(arr[queue.peek()]).append(" ");
        }

        System.out.println(sb);
    }
}
