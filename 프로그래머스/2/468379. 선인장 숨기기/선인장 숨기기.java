class Solution {
    int m, n, h, w;
    int[][] drops;

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        this.m = m;
        this.n = n;
        this.h = h;
        this.w = w;
        this.drops = drops;

        int left = 0;
        int right = drops.length;
        int bestTime = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canHide(mid) != null) {
                bestTime = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return canHide(bestTime);
    }

    int[] canHide(int time) {
        int rowSize = m - h + 1;
        int colSize = n - w + 1;

        int[][] dropCount = new int[rowSize + 1][colSize + 1];

        for (int idx = 0; idx < time; idx++) {
            int dropRow = drops[idx][0];
            int dropCol = drops[idx][1];

            int startRow = Math.max(0, dropRow - h + 1);
            int endRow = Math.min(dropRow, rowSize - 1);

            int startCol = Math.max(0, dropCol - w + 1);
            int endCol = Math.min(dropCol, colSize - 1);

            if (startRow > endRow || startCol > endCol) continue;

            dropCount[startRow][startCol]++;
            dropCount[endRow + 1][startCol]--;
            dropCount[startRow][endCol + 1]--;
            dropCount[endRow + 1][endCol + 1]++;
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (row > 0) dropCount[row][col] += dropCount[row - 1][col];
                if (col > 0) dropCount[row][col] += dropCount[row][col - 1];
                if (row > 0 && col > 0) dropCount[row][col] -= dropCount[row - 1][col - 1];

                if (dropCount[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }

        return null;
    }
}