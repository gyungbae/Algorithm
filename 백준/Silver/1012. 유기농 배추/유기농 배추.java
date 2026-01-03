import java.io.*;
import java.util.*;

public class Main {
    static int T, M, N, K;
    static int[][] map;

    static StringBuilder sb = new StringBuilder();
    static boolean[][] visited;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static boolean canProceed(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= M) {
            return false;
        }

        if (map[row][col] != 1 || visited[row][col]) {
            return false;
        }

        return true;
    }

    static void dfs(int curRow, int curCol) {
        visited[curRow][curCol] = true;

        for (int delta = 0; delta < 4; delta++) {
            int nextRow = curRow + deltaRow[delta];
            int nextCol = curCol + deltaCol[delta];

            if (canProceed(nextRow, nextCol)) {
                dfs(nextRow, nextCol);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int col = Integer.parseInt(st.nextToken());
                int row = Integer.parseInt(st.nextToken());
                map[row][col] = 1;
            }

            visited = new boolean[N][M];
            int answer = 0;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (map[row][col] == 1 && !visited[row][col]) {
                        dfs(row, col);
                        answer++;
                    }
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
