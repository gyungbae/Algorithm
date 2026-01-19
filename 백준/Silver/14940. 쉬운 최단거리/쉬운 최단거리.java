import java.io.*;
import java.util.*;

public class Main {
    static int n, m;

    static int[][] map;
    static int[][] record;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    static void BFS(int row, int col) {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{row, col});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int currentRow = current[0];
            int currentCol = current[1];

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = currentRow + deltaRow[delta];
                int nextCol = currentCol + deltaCol[delta];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                    continue;
                }

                if (map[nextRow][nextCol] != 1 || record[nextRow][nextCol] != 0) {
                    continue;
                }

                record[nextRow][nextCol] = record[currentRow][currentCol] + 1;
                q.offer(new int[]{nextRow, nextCol});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        int startRow = 0;
        int startCol = 0;
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < m; col++) {
                int value = Integer.parseInt(st.nextToken());

                if (value == 2) {
                    startRow = row;
                    startCol = col;
                }

                map[row][col] = value;
            }
        }

        record = new int[n][m];
        BFS(startRow, startCol);

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (record[row][col] == 0 && map[row][col] == 1) {
                    sb.append(-1).append(" ");
                    continue;
                }

                sb.append(record[row][col]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
