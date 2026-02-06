import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] inputArr;

    static int startRow;
    static int startCol;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static boolean[][] visited;
    static int answer;

    static void BFS() {
        Queue<int[]> queue = new ArrayDeque<>();

        visited[startRow][startCol] = true;
        queue.offer(new int[]{startRow, startCol});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (inputArr[current[0]][current[1]] == 'P') {
                answer++;
            }

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                    continue;
                }

                if (inputArr[nextRow][nextCol] == 'X' || visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputArr = new char[N][M];
        for (int row = 0; row < N; row++) {
            String line = br.readLine();
            for (int col = 0; col < M; col++) {
                char ch = line.charAt(col);
                inputArr[row][col] = ch;
                if (ch == 'I') {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        visited = new boolean[N][M];
        BFS();

        System.out.println(answer == 0 ? "TT" : answer);
    }
}
