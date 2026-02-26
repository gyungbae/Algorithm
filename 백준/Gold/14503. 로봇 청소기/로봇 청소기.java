import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] inputArr;

    static Cleaner cleaner;
    static int answer;
    static boolean[][] cleaned;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static void clean() {
        if (!cleaned[cleaner.row][cleaner.col]) {
            cleaned[cleaner.row][cleaner.col] = true;
            answer++;
        }
    }

    static boolean canProceed() {
        for (int delta = 0; delta < 4; delta++) {
            int nextRow = cleaner.row + deltaRow[delta];
            int nextCol = cleaner.col + deltaCol[delta];

            if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
                continue;

            if(cleaned[nextRow][nextCol] || inputArr[nextRow][nextCol] == 1)
                continue;

            return true;
        }

        return false;
    }

    static void rotate() {
        if (cleaner.direction == 0) {
            cleaner.direction = 3;
        } else {
            cleaner.direction--;
        }
    }

    static void moveBackward() {
        int nextRow = cleaner.row;
        int nextCol = cleaner.col;
        switch (cleaner.direction) {
            case 0 -> { //북
                nextRow = cleaner.row + 1;
            }

            case 1 -> { //동
                nextCol = cleaner.col - 1;
            }

            case 2 -> { //남
                nextRow = cleaner.row - 1;
            }

            default -> {    //서
                nextCol = cleaner.col + 1;
            }
        }

        if(canBackward(nextRow, nextCol))
            move(nextRow, nextCol);
    }

    static boolean canBackward(int row, int col) {
        if(row < 0 || row >= N || col < 0 || col >= M)
            return false;

        if(inputArr[row][col] == 1)
            return false;

        return true;
    }

    static void moveForward() {
        int nextRow = cleaner.row;
        int nextCol = cleaner.col;
        switch (cleaner.direction) {
            case 0 -> { //북
                nextRow = cleaner.row - 1;
            }
            case 1 -> { //동
                nextCol = cleaner.col + 1;
            }
            case 2 -> { //남
                nextRow = cleaner.row + 1;
            }
            default -> {    //서
                nextCol = cleaner.col - 1;
            }
        }

        if (canForward(nextRow, nextCol)) {
            move(nextRow, nextCol);
        } else {
            rotate();
            moveForward();
        }
    }

    static boolean canForward(int row, int col){
        if(row < 0 || row >= N || col < 0 || col >= M)
            return false;

        if(cleaned[row][col] || inputArr[row][col] == 1)
            return false;

        return true;
    }

    static void move(int row, int col) {
        cleaner.row = row;
        cleaner.col = col;
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

            if(!canProceed()) {
                int previousRow = cleaner.row;
                int previousCol = cleaner.col;

                moveBackward();

                if(cleaner.row == previousRow && cleaner.col == previousCol)
                    break;
            } else {
                rotate();
                moveForward();
            }
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
