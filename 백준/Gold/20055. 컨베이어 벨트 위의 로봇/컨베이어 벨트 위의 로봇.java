import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] input;

    static Queue<Integer> queue = new ArrayDeque<>();
    static int zeroCount;
    static int answer;

    static void rotate() {
        int last = input[N * 2];
        for (int idx = N * 2; idx >= 2; idx--) {
            input[idx] = input[idx - 1];
        }
        input[1] = last;

        int size = queue.size();
        while (size-- > 0) {
            int position = queue.poll();

            int next = position + 1;
            if(position == N * 2)
                next = 1;

            if(next == N)
                continue;

            queue.offer(next);
        }
    }

    static void moveRobot() {
        int size = queue.size();
        while (size-- > 0) {
            int position = queue.poll();

            int next = position + 1;
            if(position == N * 2)
                next = 1;

            if (input[next] > 0 && !queue.contains(next)) {
                input[next]--;
                if(input[next] == 0)
                    zeroCount++;

                if(next != N)
                    queue.offer(next);
            } else {
                queue.offer(position);
            }
        }
    }

    static void putRobot() {
        if (input[1] > 0) {
            input[1]--;
            if(input[1] == 0)
                zeroCount++;

            queue.offer(1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new int[N * 2 + 1];
        st = new StringTokenizer(br.readLine());
        for (int idx = 1; idx <= N * 2; idx++) {
            input[idx] = Integer.parseInt(st.nextToken());
        }

        while(zeroCount < K) {
            rotate();
            moveRobot();
            putRobot();
            answer++;
        }

        System.out.println(answer);
    }
}
