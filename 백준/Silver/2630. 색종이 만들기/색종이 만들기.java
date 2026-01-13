import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;

    static int whiteCnt, blueCnt = 0;
    static StringBuilder sb = new StringBuilder();

    static void search(int stdRow, int stdCol, int size) {
        if (check(stdRow, stdCol, size)) {
            if (arr[stdRow][stdCol] == 0) {
                whiteCnt++;
            } else {
                blueCnt++;
            }

            return;
        }

        int half = size / 2;
        search(stdRow, stdCol, half);
        search(stdRow + half, stdCol, half);
        search(stdRow, stdCol + half, half);
        search(stdRow + half, stdCol + half, half);
    }

    static boolean check(int stdRow, int stdCol, int size) {
        int val = arr[stdRow][stdCol];
        for (int row = stdRow; row < stdRow + size; row++) {
            for (int col = stdCol; col < stdCol + size; col++) {
                if (arr[row][col] != val) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                arr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        search(0, 0, N);

        sb.append(whiteCnt).append("\n").append(blueCnt);
        System.out.println(sb);
    }
}
