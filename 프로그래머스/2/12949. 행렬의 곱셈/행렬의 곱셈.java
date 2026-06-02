class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {

        int rowSize = arr1.length;
        int colSize = arr2[0].length;
        int multiplyCount = arr2.length;

        int[][] answer = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {

                for (int multiplyIdx = 0; multiplyIdx < multiplyCount; multiplyIdx++) {
                    answer[row][col] +=
                            arr1[row][multiplyIdx] * arr2[multiplyIdx][col];
                }
            }
        }

        return answer;
    }
}