import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[][] map;

    static int score;

    static boolean isFullRow(int row) {
        boolean isFull = true;
        for (int col = 0; col < 4; col++) {
            if (!map[row][col]) {
                isFull = false;
                break;
            }
        }

        return isFull;
    }

    static boolean isFullCol(int col) {
        boolean isFull = true;
        for (int row = 0; row < 4; row++) {
            if (!map[row][col]) {
                isFull = false;
                break;
            }
        }

        return isFull;
    }

    static boolean hasInLightRow() {
        boolean has = false;
        for (int col = 0; col < 4; col++) {
            if (map[5][col]) {
                has = true;
                break;
            }
        }

        return has;
    }

    static boolean hasInLightCol() {
        boolean has = false;
        for (int row = 0; row < 4; row++) {
            if (map[row][5]) {
                has = true;
                break;
            }
        }

        return has;
    }

    static void moveRow(int fromRow) {
        for (int row = fromRow; row >= 4; row--) {
            for (int col = 0; col < 4; col++) {
                map[row][col] = map[row - 1][col];
            }
        }
    }

    static void moveCol(int fromCol) {
        for (int col = fromCol; col >= 4; col--) {
            for (int row = 0; row < 4; row++) {
                map[row][col] = map[row][col - 1];
            }
        }
    }

    static void put1(int putRow, int putCol) {
        int currentRow = putRow;
        int currentCol = putCol;

        while (true) {
            if (currentRow == 9 || map[currentRow + 1][currentCol]) {
                map[currentRow][currentCol] = true;
                break;
            }

            currentRow++;
        }

        if (isFullRow(currentRow)) {
            score++;

            for (int col = 0; col < 4; col++) {
                map[currentRow][col] = false;
            }

            moveRow(currentRow);
        }

        if (hasInLightRow()) {
            for (int col = 0; col < 4; col++) {
                map[9][col] = false;
            }

            moveRow(9);
        }

        currentRow = putRow;
        currentCol = putCol;
        while (true) {
            if (currentCol == 9 || map[currentRow][currentCol + 1]) {
                map[currentRow][currentCol] = true;
                break;
            }

            currentCol++;
        }

        if (isFullCol(currentCol)) {
            score++;

            for (int row = 0; row < 4; row++) {
                map[row][currentCol] = false;
            }

            moveCol(currentCol);
        }

        if (hasInLightCol()) {
            for (int row = 0; row < 4; row++) {
                map[row][9] = false;
            }

            moveCol(9);
        }
    }

    static void put2(int putRow, int putCol) {
        int currentRow = putRow;
        int currentCol1 = putCol;
        int currentCol2 = putCol + 1;

        while (true) {
            if (currentRow == 9 || map[currentRow + 1][currentCol1] || map[currentRow + 1][currentCol2]) {
                map[currentRow][currentCol1] = true;
                map[currentRow][currentCol2] = true;
                break;
            }

            currentRow++;
        }

        if (isFullRow(currentRow)) {
            score++;

            for (int col = 0; col < 4; col++) {
                map[currentRow][col] = false;
            }

            moveRow(currentRow);
        }

        if (hasInLightRow()) {
            for (int col = 0; col < 4; col++) {
                map[9][col] = false;
            }

            moveRow(9);
        }

        currentRow = putRow;
        currentCol1 = putCol;
        currentCol2 = putCol + 1;
        while (true) {
            if (currentCol2 == 9 || map[currentRow][currentCol2 + 1]) {
                map[currentRow][currentCol1] = true;
                map[currentRow][currentCol2] = true;
                break;
            }

            currentCol1++;
            currentCol2++;
        }

        if (isFullCol(currentCol1)) {
            score++;

            for (int row = 0; row < 4; row++) {
                map[row][currentCol1] = false;
            }

            moveCol(currentCol1);
        }

        if (isFullCol(currentCol2)) {
            score++;

            for (int row = 0; row < 4; row++) {
                map[row][currentCol2] = false;
            }

            moveCol(currentCol2);
        }

        if (hasInLightCol()) {
            for (int row = 0; row < 4; row++) {
                map[row][9] = false;
            }

            moveCol(9);
        }

        if (hasInLightCol()) {
            for (int row = 0; row < 4; row++) {
                map[row][9] = false;
            }

            moveCol(9);
        }
    }

    static void put3(int putRow, int putCol) {
        int currentRow1 = putRow;
        int currentRow2 = putRow + 1;
        int currentCol = putCol;

        while (true) {
            if (currentRow2 == 9 || map[currentRow2 + 1][currentCol]) {
                map[currentRow1][currentCol] = true;
                map[currentRow2][currentCol] = true;
                break;
            }

            currentRow1++;
            currentRow2++;
        }

        if (isFullRow(currentRow1)) {
            score++;

            for (int col = 0; col < 4; col++) {
                map[currentRow1][col] = false;
            }

            moveRow(currentRow1);
        }

        if (isFullRow(currentRow2)) {
            score++;

            for (int col = 0; col < 4; col++) {
                map[currentRow2][col] = false;
            }

            moveRow(currentRow2);
        }

        if (hasInLightRow()) {
            for (int col = 0; col < 4; col++) {
                map[9][col] = false;
            }

            moveRow(9);
        }

        if (hasInLightRow()) {
            for (int col = 0; col < 4; col++) {
                map[9][col] = false;
            }

            moveRow(9);
        }

        currentRow1 = putRow;
        currentRow2 = putRow + 1;
        currentCol = putCol;
        while (true) {
            if (currentCol == 9 || map[currentRow1][currentCol + 1] || map[currentRow2][currentCol + 1]) {
                map[currentRow1][currentCol] = true;
                map[currentRow2][currentCol] = true;
                break;
            }

            currentCol++;
        }

        if (isFullCol(currentCol)) {
            score++;

            for (int row = 0; row < 4; row++) {
                map[row][currentCol] = false;
            }

            moveCol(currentCol);
        }

        if (hasInLightCol()) {
            for (int row = 0; row < 4; row++) {
                map[row][9] = false;
            }

            moveCol(9);
        }
    }

    static int getTiles() {
        int count = 0;

        for (int row = 6; row < 10; row++) {
            for (int col = 0; col < 4; col++) {
                if(map[row][col])
                    count++;
            }
        }

        for (int col = 6; col < 10; col++) {
            for (int row = 0; row < 4; row++) {
                if(map[row][col])
                    count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new boolean[10][10];
        N = Integer.parseInt(br.readLine());
        for (int input = 1; input <= N; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            if (type == 1) {
                put1(row, col);
            } else if (type == 2) {
                put2(row, col);
            } else {
                put3(row, col);
            }
        }

        System.out.println(score + "\n" + getTiles());
    }
}
