import java.util.*;

class Solution {
    int[][] map;
    int size;

    int[] deltaRow = {-1, 0, 1, 0};
    int[] deltaCol = {0, 1, 0, -1};

    int[][][] cost;

    void search(int row, int col, int currentDelta, int currentCost) {
        if (currentCost > cost[row][col][currentDelta]) {
            return;
        }

        for (int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow[delta];
            int nextCol = col + deltaCol[delta];

            if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) {
                continue;
            }

            if (map[nextRow][nextCol] == 1) {
                continue;
            }

            int nextCost = currentCost + 100;

            if (delta != currentDelta) {
                nextCost += 500;
            }

            if (nextCost < cost[nextRow][nextCol][delta]) {
                cost[nextRow][nextCol][delta] = nextCost; 
                search(nextRow, nextCol, delta, nextCost);
            }
        }
    }

    public int solution(int[][] board) {
        map = board;
        size = board.length;

        cost = new int[size][size][4];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Arrays.fill(cost[row][col], Integer.MAX_VALUE);
            }
        }

        for (int delta = 0; delta < 4; delta++) {
            cost[0][0][delta] = 0;
            search(0, 0, delta, 0);
        }

        int answer = Integer.MAX_VALUE;

        for (int delta = 0; delta < 4; delta++) {
            answer = Math.min(answer, cost[size - 1][size - 1][delta]);
        }

        return answer;
    }
}