import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] inputArr;

    static boolean[][] visited;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static int maxSum;

    static void DFS(int row, int col, int sum, int depth) {
        if (depth == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow[delta];
            int nextCol = col + deltaCol[delta];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                continue;
            }

            if (visited[nextRow][nextCol]) {
                continue;
            }

            if (depth == 2) {
                visited[nextRow][nextCol] = true;
                DFS(row, col, sum + inputArr[nextRow][nextCol], depth + 1);
                visited[nextRow][nextCol] = false;
            }

            visited[nextRow][nextCol] = true;
            DFS(nextRow, nextCol, sum + inputArr[nextRow][nextCol], depth + 1);
            visited[nextRow][nextCol] = false;
        }
    }

    static void checkT(int row, int col) {

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inputArr = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                inputArr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                visited[row][col] = true;
                DFS(row, col, inputArr[row][col], 1);
                visited[row][col] = false;
            }
        }

        System.out.println(maxSum);
    }
}
