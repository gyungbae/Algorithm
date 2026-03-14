import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int answer;

    static int[] moveDeltaRow = {0, 1, 0, -1};
    static int[] moveDeltaCol = {-1, 0, 1, 0};

    static int[] blowDeltaRow = {-1, 1, -1, 1, 0, -2, 2, -1, 1};
    static int[] blowDeltaCol = {-1, -1, 0, 0, -2, 0, 0, 1, 1};
    static int[] percent = {10, 10, 7, 7, 5, 2, 2, 1, 1};

    static Tornado tornado;

    static void move() {
        tornado.row += moveDeltaRow[tornado.direction];
        tornado.col += moveDeltaCol[tornado.direction];

        blow();

        tornado.moveCount++;
        if (tornado.moveCount == tornado.step) {
            tornado.turn();
            tornado.moveCount = 0;

            tornado.stepCount++;
            if (tornado.stepCount == 2) {
                tornado.step++;
                tornado.stepCount = 0;
            }
        }
    }

    static void blow() {
        int row = tornado.row;
        int col = tornado.col;
        int direction = tornado.direction;
        int sand = map[row][col];

        int sum = 0;
        for (int delta = 0; delta < 9; delta++) {
            int[] nextInfo = rotate(blowDeltaRow[delta], blowDeltaCol[delta], direction);
            int nextRow = row + nextInfo[0];
            int nextCol = col + nextInfo[1];

            int amount = sand * percent[delta] / 100;
            sum += amount;

            if (!isInRange(nextRow, nextCol)) {
                answer += amount;
            } else {
                map[nextRow][nextCol] += amount;
            }
        }

        int alphaRow = row + moveDeltaRow[direction];
        int alphaCol = col + moveDeltaCol[direction];
        int alphaAmount = sand - sum;

        if (!isInRange(alphaRow, alphaCol)) {
            answer += alphaAmount;
        } else {
            map[alphaRow][alphaCol] += alphaAmount;
        }

        map[row][col] = 0;
    }

    static int[] rotate(int row, int col, int direction) {
        for (int count = 0; count < direction; count++) {
            int temp = row;
            row = -col;
            col = temp;
        }

        return new int[]{row, col};
    }

    static boolean isInRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        tornado = new Tornado(N / 2, N / 2);

        while (tornado.row != 0 || tornado.col != 0) {
            move();
        }

        System.out.println(answer);
    }
}

class Tornado {
    int row, col;
    int step, stepCount, moveCount, direction;

    public Tornado(int row, int col) {
        this.row = row;
        this.col = col;
        this.step = 1;
        this.direction = 0;
    }

    void turn() {
        direction = (direction + 1) % 4;
    }
}
