import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] input;

    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static boolean[][] visited;
    static int maxSum;

    static void search(int row, int col, int sum, int depth) {
        if (depth == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow[delta];
            int nextCol = col + deltaCol[delta];

            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                continue;

            if(visited[nextRow][nextCol])
                continue;

            if(depth == 2) {
                visited[nextRow][nextCol] = true;
                search(row, col, sum + input[nextRow][nextCol], depth + 1);
                visited[nextRow][nextCol] = false;
            }

            visited[nextRow][nextCol] = true;
            search(nextRow, nextCol, sum + input[nextRow][nextCol], depth + 1);
            visited[nextRow][nextCol] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                input[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                visited[row][col] = true;
                search(row, col, input[row][col], 1);
                visited[row][col] = false;
            }
        }

        System.out.println(maxSum);
    }
}
