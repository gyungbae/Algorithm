class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            if (isSame(mat, target)) 
                return true;
            
            mat = rotate(mat);
        }

        return false;
    }

    private int[][] rotate(int[][] mat) {
        int n = mat.length;
        int[][] rotated = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                rotated[col][n - 1 - row] = mat[row][col];
            }
        }

        return rotated;
    }

    private boolean isSame(int[][] mat, int[][] target) {
        int n = mat.length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (mat[row][col] != target[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }
}
