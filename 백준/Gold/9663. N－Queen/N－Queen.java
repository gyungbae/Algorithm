import java.io.*;
import java.util.*;

public class Main {
    static int N;

    static int[][] stateArr;
    static int answer = 0;

    static void DFS(int row) {
        if (row == N) {
            answer++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (stateArr[row][col] != 0) {
                continue;
            }

            updateState(row, col, true);
            DFS(row + 1);
            updateState(row, col, false);
        }
    }

    static void updateState(int standardRow, int standardCol, boolean selected) {
        int delta = selected ? 1 : -1;

        for (int row = standardRow; row < N; row++) {
            stateArr[row][standardCol] += delta;
        }

        for (int row = standardRow, col = standardCol; row < N && col < N; row++, col++) {
            stateArr[row][col] += delta;
        }

        for (int row = standardRow, col = standardCol; row < N && col >= 0; row++, col--) {
            stateArr[row][col] += delta;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        stateArr = new int[N][N];

        DFS(0);

        System.out.println(answer);
    }
}
