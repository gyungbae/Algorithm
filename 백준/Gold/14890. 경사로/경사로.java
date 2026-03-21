import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;

    static int answer;

    static void checkRow(int row) {
        int num = map[row][0];
        boolean[] placed = new boolean[N];

        int colIdx = 1;
        while (true) {
            if(colIdx == N)
                break;

            int nextNum = map[row][colIdx];

            if (num == nextNum) {
                colIdx++;
                continue;
            }

            if (Math.abs(num - nextNum) > 1)
                return;

            if (num > nextNum) {
                for (int col = colIdx; col < colIdx + L; col++) {
                    if(col >= N)
                        return;

                    if (placed[col])
                        return;

                    if(map[row][col] != nextNum)
                        return;

                    placed[col] = true;
                }

                colIdx += L;
            }

            else {
                for (int col = colIdx - 1; col >= colIdx - L; col--) {
                    if(col < 0)
                        return;

                    if (placed[col])
                        return;

                    if(map[row][col] != num)
                        return;

                    placed[col] = true;
                }

                colIdx++;
            }

            num = nextNum;
        }

        answer++;
    }

    static void checkCol(int col) {
        int num = map[0][col];
        boolean[] placed = new boolean[N];

        int rowIdx = 1;
        while (true) {
            if(rowIdx == N)
                break;

            int nextNum = map[rowIdx][col];

            if (num == nextNum) {
                rowIdx++;
                continue;
            }

            if (Math.abs(num - nextNum) > 1)
                return;

            if (num > nextNum) {
                for (int row = rowIdx; row < rowIdx + L; row++) {
                    if(row >= N)
                        return;

                    if (placed[row])
                        return;

                    if(map[row][col] != nextNum)
                        return;

                    placed[row] = true;
                }

                rowIdx += L;
            }

            else {
                for (int row = rowIdx - 1; row >= rowIdx - L; row--) {
                    if(row < 0)
                        return;

                    if (placed[row])
                        return;

                    if(map[row][col] != num)
                        return;

                    placed[row] = true;
                }

                rowIdx++;
            }

            num = nextNum;
        }

        answer++;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 0; row < N; row++) {
            checkRow(row);
        }

        for (int col = 0; col < N; col++) {
            checkCol(col);
        }

        System.out.println(answer);
    }
}
