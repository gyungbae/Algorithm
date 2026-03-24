import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] add;
    static int[][] map;

    static Queue<int[]> liveQueue = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
    static List<int[]> deadList = new ArrayList<>();
    static int[] deltaRow = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] deltaCol = {-1, 0, 1, -1, 1, -1, 0, 1};

    static void spring() {
        Queue<int[]> nextQueue = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        while (!liveQueue.isEmpty()) {
            int[] info = liveQueue.poll();
            int row = info[0];
            int col = info[1];
            int age = info[2];

            if(map[row][col] < age) {
                deadList.add(info);
            } else {
                map[row][col] -= age;
                nextQueue.offer(new int[]{row, col, age + 1});
            }
        }

        liveQueue = nextQueue;
    }

    static void summer() {
        for (int[] info : deadList) {
            map[info[0]][info[1]] += info[2] / 2;
        }

        deadList.clear();
    }

    static void autumn() {
        List<int[]> newList = new ArrayList<>();

        while (!liveQueue.isEmpty()) {
            int[] info = liveQueue.poll();
            int row = info[0];
            int col = info[1];
            int age = info[2];

            newList.add(info);

            if (age % 5 != 0)
                continue;

            for (int delta = 0; delta < 8; delta++) {
                int nextRow = row + deltaRow[delta];
                int nextCol = col + deltaCol[delta];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                    continue;

                newList.add(new int[]{nextRow, nextCol, 1});
            }
        }

        for (int[] info : newList) {
            liveQueue.offer(info);
        }
    }

    static void winter() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                map[row][col] += add[row][col];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        add = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                add[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int tree = 1; tree <= M; tree++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            liveQueue.offer(new int[]{row, col, age});
        }

        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            Arrays.fill(map[row], 5);
        }

        while (K-- > 0) {
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.println(liveQueue.size());
    }
}
