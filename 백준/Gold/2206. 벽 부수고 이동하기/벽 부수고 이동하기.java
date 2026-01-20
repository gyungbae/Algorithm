import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    static int[][][] dist;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static int BFS() {
        Queue<int[]> q = new ArrayDeque<>();

        dist[0][0][0] = 1;
        q.offer(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int curRow = current[0];
            int curCol = current[1];
            int broken = current[2];

            if (curRow == N - 1 && curCol == M - 1) {
                return dist[curRow][curCol][broken];
            }

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = curRow + deltaRow[delta];
                int nextCol = curCol + deltaCol[delta];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                    continue;
                }

                if (dist[nextRow][nextCol][broken] != -1) {
                    continue;
                }

                if (map[nextRow][nextCol] == 1) {
                    if (broken == 1) {
                        continue;
                    }

                    dist[nextRow][nextCol][1] = dist[curRow][curCol][0] + 1;
                    q.offer(new int[]{nextRow, nextCol, 1});
                    continue;
                }

                dist[nextRow][nextCol][broken] = dist[curRow][curCol][broken] + 1;
                q.offer(new int[]{nextRow, nextCol, broken});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            String line = br.readLine();
            for (int col = 0; col < M; col++) {
                map[row][col] = line.charAt(col) - '0';
            }
        }

        dist = new int[N][M][2];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                Arrays.fill(dist[row][col], -1);
            }
        }

        System.out.println(BFS());
    }
}
