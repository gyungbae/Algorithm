class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int[][] tmp = new int[k][k];
        for(int row = 0; row <k; row++) {
            for(int col = 0; col < k; col++) {
                tmp[row][col] = grid[x + k - row - 1][y + col];
            }
        }

        for(int row = 0; row < k; row++) {
            for(int col = 0; col < k; col++) {
                grid[row + x][col + y] = tmp[row][col];
            }
        }

        return grid;
    }
}