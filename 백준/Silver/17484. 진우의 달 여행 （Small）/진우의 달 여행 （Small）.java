import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    static int[] deltaCol = {-1, 0, 1};

    static void dfs(int row, int col, int direction, int sum) {
        if (row == N - 1) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int delta = 0; delta < 3; delta++) {
            if (delta == direction) 
                continue; 

            int nextRow = row + 1;
            int nextCol = col + deltaCol[delta];

            if (nextCol < 0 || nextCol >= M) 
                continue;

            dfs(nextRow, nextCol, delta, sum + map[nextRow][nextCol]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            dfs(0, i, -1, map[0][i]);
        }

        System.out.println(answer);
    }
}
