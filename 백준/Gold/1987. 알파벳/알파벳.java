import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] input;

    static boolean[][] visited;
    static boolean[] selected;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static int answer;

    static void DFS(int row, int col, int depth) {
        if (depth > answer) {
            answer = depth;
        }

        for (int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow[delta];
            int nextCol = col + deltaCol[delta];

            if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) {
                continue;
            }

            int alphabetIdx = input[nextRow][nextCol] - 'A';

            if (visited[nextRow][nextCol] || selected[alphabetIdx]) {
                continue;
            }

            visited[nextRow][nextCol] = true;
            selected[alphabetIdx] = true;
            DFS(nextRow, nextCol, depth + 1);
            visited[nextRow][nextCol] = false;
            selected[alphabetIdx] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        input = new char[R][C];
        for (int row = 0; row < R; row++) {
            String line = br.readLine();
            for (int col = 0; col < C; col++) {
                input[row][col] = line.charAt(col);
            }
        }

        visited = new boolean[R][C];
        selected = new boolean[26];
        visited[0][0] = true;
        selected[input[0][0] - 'A'] = true;
        answer = 1;
        DFS(0, 0, 1);

        System.out.println(answer);
    }
}
