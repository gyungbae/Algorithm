class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int size = n * m;
        int mod = 12345;

        int[] arr = new int[size];
        int idx = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                arr[idx++] = grid[row][col] % mod;
            }
        }

        int[] prefix = new int[size];
        prefix[0] = 1;
        for (int i = 1; i < size; i++) {
            prefix[i] = (int) ((long) prefix[i - 1] * arr[i - 1] % mod);
        }

        int[] suffix = new int[size];
        suffix[size - 1] = 1;
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = (int) ((long) suffix[i + 1] * arr[i + 1] % mod);
        }

        int[][] result = new int[n][m];
        idx = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                result[row][col] = (int) ((long) prefix[idx] * suffix[idx] % mod);
                idx++;
            }
        }

        return result;
    }
}