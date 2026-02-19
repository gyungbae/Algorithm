import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    static int[] timeArr;
    static int count = 0;

    static void search() {
        Queue<Integer> queue = new ArrayDeque<>();
        timeArr = new int[100001];
        Arrays.fill(timeArr, Integer.MAX_VALUE);

        timeArr[N] = 0;
        queue.offer(N);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == K) {
                count++;
            }

            int next = current - 1;
            int nextTime = timeArr[current] + 1;
            if (next >= 0 && nextTime <= timeArr[next]) {
                timeArr[next] = nextTime;
                queue.offer(next);
            }

            next = current + 1;
            if (next <= 100000 && nextTime <= timeArr[next]) {
                timeArr[next] = nextTime;
                queue.offer(next);
            }

            next = current * 2;
            if (next <= 100000 && nextTime <= timeArr[next]) {
                timeArr[next] = nextTime;
                queue.offer(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        search();

        System.out.println(timeArr[K] + "\n" + count);
    }
}
