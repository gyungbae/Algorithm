import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] inputArr;

    //spread()
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    //clean()
    static int cleanerRow1, cleanerRow2;

    static void spread() {
        List<int[]> spreadList = new ArrayList<>();
        for (int row = 1; row <= R; row++) {
            for (int col = 1; col <= C; col++) {
                if (inputArr[row][col] != 0 && inputArr[row][col] != -1) {
                    spreadList.add(new int[]{row, col, inputArr[row][col]});
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        for (int[] info : spreadList) {
            queue.offer(info);
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int spreadSum = 0;
            for (int delta = 0; delta < 4; delta++) {
                int row = current[0] + deltaRow[delta];
                int col = current[1] + deltaCol[delta];

                if (row < 1 || row > R || col < 1 || col > C) {
                    continue;
                }

                if (inputArr[row][col] == -1) {
                    continue;
                }

                int amount = current[2] / 5;
                inputArr[row][col] += amount;
                spreadSum += amount;
            }

            inputArr[current[0]][current[1]] -= spreadSum;
        }
    }

    static void clean() {
        for (int row = cleanerRow1 - 1; row > 1; row--) {
            inputArr[row][1] = inputArr[row - 1][1];
        }

        for (int col = 1; col < C; col++) {
            inputArr[1][col] = inputArr[1][col + 1];
        }

        for (int row = 1; row < cleanerRow1; row++) {
            inputArr[row][C] = inputArr[row + 1][C];
        }

        for (int col = C; col > 2; col--) {
            inputArr[cleanerRow1][col] = inputArr[cleanerRow1][col - 1];
        }

        inputArr[cleanerRow1][2] = 0;

        for (int row = cleanerRow2 + 1; row < R; row++) {
            inputArr[row][1] = inputArr[row + 1][1];
        }

        for (int col = 1; col < C; col++) {
            inputArr[R][col] = inputArr[R][col + 1];
        }

        for (int row = R; row > cleanerRow2; row--) {
            inputArr[row][C] = inputArr[row - 1][C];
        }

        for (int col = C; col > 1; col--) {
            inputArr[cleanerRow2][col] = inputArr[cleanerRow2][col - 1];
        }

        inputArr[cleanerRow2][2] = 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        inputArr = new int[R + 1][C + 1];
        cleanerRow1 = -1;
        cleanerRow2 = -1;
        for (int row = 1; row <= R; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= C; col++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == -1) {
                    if (cleanerRow1 == -1) {
                        cleanerRow1 = row;
                    } else {
                        cleanerRow2 = row;
                    }
                }

                inputArr[row][col] = num;
            }
        }

        while (T-- > 0) {
            spread();
            clean();
        }

        int answer = 0;
        for (int row = 1; row <= R; row++) {
            for (int col = 1; col <= C; col++) {
                if (inputArr[row][col] != -1) {
                    answer += inputArr[row][col];
                }
            }
        }

        System.out.println(answer);
    }
}
