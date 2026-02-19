import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] input;

    static boolean[][] outside;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static boolean melt() {
        outside = new boolean[N][M];
        checkAir();

        List<int[]> meltList = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (input[row][col] == 1 && isMeltingCheeze(row, col)) {
                    meltList.add(new int[]{row, col});
                }
            }
        }

        boolean melted = false;
        for (int[] info : meltList) {
            input[info[0]][info[1]] = 0;
            melted = true;
        }

        return melted;
    }

    static boolean isMeltingCheeze(int row, int col) {
        int count = 0;
        for (int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow[delta];
            int nextCol = col + deltaCol[delta];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                continue;

            if (input[nextRow][nextCol] == 0 && outside[nextRow][nextCol])
                count++;
        }

        return count >= 2;
    }

    static void checkAir() {
        Queue<int[]> queue = new ArrayDeque<>();

        outside[0][0] = true;
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int delta = 0; delta < 4; delta++) {
                int row = current[0] + deltaRow[delta];
                int col = current[1] + deltaCol[delta];

                if (row < 0 || row >= N || col < 0 || col >= M) 
                    continue;
                
                if (outside[row][col] || input[row][col] != 0) 
                    continue;
                
                outside[row][col] = true;
                queue.offer(new int[]{row, col});
            }
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

        int answer = 0;
        while (melt()) {
            answer++;
        }

        System.out.println(answer);
    }
}
