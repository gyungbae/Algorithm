import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] map;

    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();

    static void DFS1(int row, int col) {
        visited[row][col] = true;

        for (int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow[delta];
            int nextCol = col + deltaCol[delta];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                continue;
            }

            if (visited[nextRow][nextCol] || map[row][col] != map[nextRow][nextCol]) {
                continue;
            }

            DFS1(nextRow, nextCol);
        }
    }

    static void DFS2(int row, int col, char ch) {
        visited[row][col] = true;

        for (int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow[delta];
            int nextCol = col + deltaCol[delta];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                continue;
            }
            
            if (visited[nextRow][nextCol]) {
                continue;
            }

            char nextColor = map[nextRow][nextCol];
            if (ch == 'B' && nextColor != 'B') {
                continue;
            }

            if (ch != 'B' && nextColor == 'B') {
                continue;
            }

            DFS2(nextRow, nextCol, nextColor);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int row = 0; row < N; row++) {
            String line = br.readLine();
            for (int col = 0; col < N; col++) {
                map[row][col] = line.charAt(col);
            }
        }

        visited = new boolean[N][N];
        int area = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (!visited[row][col]) {
                    area++;
                    DFS1(row, col);
                }
            }
        }
        sb.append(area).append("\n");

        visited = new boolean[N][N];
        area = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (!visited[row][col]) {
                    area++;
                    DFS2(row, col, map[row][col]);
                }
            }
        }
        sb.append(area).append("\n");

        System.out.println(sb);
    }
}
