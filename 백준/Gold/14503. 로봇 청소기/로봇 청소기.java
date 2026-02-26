import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] inputArr;          // 0: 빈칸, 1: 벽

    static boolean[][] cleaned;
    static Cleaner cleaner;
    static int answer;

    static final int[] DELTA_ROW = {-1, 0, 1, 0};
    static final int[] DELTA_COL = {0, 1, 0, -1};

    static void clean() {
        if (!cleaned[cleaner.row][cleaner.col]) {
            cleaned[cleaner.row][cleaner.col] = true;
            answer++;
        }
    }

    static boolean canProceed(int row, int col) {
        if(row < 0 || row >= N || col < 0 || col >= M)
            return false;

        if(inputArr[row][col] == 1)
            return false;

        return true;
    }

    static void rotate() {
        cleaner.direction = (cleaner.direction + 3) % 4;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int cleanerRow = Integer.parseInt(st.nextToken());
        int cleanerCol = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        cleaner = new Cleaner(cleanerRow, cleanerCol, direction);

        inputArr = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                inputArr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        cleaned = new boolean[N][M];

        while (true) {
            clean();

            boolean moved = false;
            for (int rotateCount = 0; rotateCount < 4; rotateCount++) {
                rotate();
                int nextRow = cleaner.row + DELTA_ROW[cleaner.direction];
                int nextCol = cleaner.col + DELTA_COL[cleaner.direction];

                if (canProceed(nextRow, nextCol) && !cleaned[nextRow][nextCol]) {
                    cleaner.row = nextRow;
                    cleaner.col = nextCol;
                    moved = true;
                    break;
                }
            }

            if (moved)
                continue;

            int nextRow = cleaner.row + DELTA_ROW[cleaner.direction] * -1;
            int nextCol = cleaner.col + DELTA_COL[cleaner.direction] * -1;

            if (!canProceed(nextRow, nextCol))
                break;

            cleaner.row = nextRow;
            cleaner.col = nextCol;
        }

        System.out.println(answer);
    }
}

class Cleaner {
    int row, col, direction;

    public Cleaner(int row, int col, int direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }
}
