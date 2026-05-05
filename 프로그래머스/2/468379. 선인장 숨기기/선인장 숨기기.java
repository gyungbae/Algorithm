import java.util.*;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int INF = drops.length + 1;

        int[][] time = new int[m][n];

        for (int row = 0; row < m; row++) {
            Arrays.fill(time[row], INF);
        }

        for (int idx = 0; idx < drops.length; idx++) {
            int row = drops[idx][0];
            int col = drops[idx][1];

            if (time[row][col] == INF) {
                time[row][col] = idx + 1;
            }
        }

        int rowSize = m;
        int colSize = n - w + 1;

        int[][] rowMin = new int[rowSize][colSize];

        for (int row = 0; row < m; row++) {
            Deque<Integer> deque = new ArrayDeque<>();

            for (int col = 0; col < n; col++) {
                while (!deque.isEmpty() && time[row][deque.peekLast()] >= time[row][col]) {
                    deque.pollLast();
                }

                deque.offerLast(col);

                if (deque.peekFirst() <= col - w) {
                    deque.pollFirst();
                }

                if (col >= w - 1) {
                    rowMin[row][col - w + 1] = time[row][deque.peekFirst()];
                }
            }
        }

        int answerRowSize = m - h + 1;
        int answerColSize = n - w + 1;

        int[][] areaMin = new int[answerRowSize][answerColSize];

        for (int col = 0; col < colSize; col++) {
            Deque<Integer> deque = new ArrayDeque<>();

            for (int row = 0; row < m; row++) {
                while (!deque.isEmpty() && rowMin[deque.peekLast()][col] >= rowMin[row][col]) {
                    deque.pollLast();
                }

                deque.offerLast(row);

                if (deque.peekFirst() <= row - h) {
                    deque.pollFirst();
                }

                if (row >= h - 1) {
                    int startRow = row - h + 1;
                    areaMin[startRow][col] = rowMin[deque.peekFirst()][col];
                }
            }
        }

        int bestTime = -1;
        int bestRow = 0;
        int bestCol = 0;

        for (int row = 0; row < answerRowSize; row++) {
            for (int col = 0; col < answerColSize; col++) {
                if (areaMin[row][col] > bestTime) {
                    bestTime = areaMin[row][col];
                    bestRow = row;
                    bestCol = col;
                }
            }
        }

        return new int[]{bestRow, bestCol};
    }
}