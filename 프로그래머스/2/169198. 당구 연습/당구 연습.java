class Solution {
    int getSquareDist(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return dx * dx + dy * dy;
    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int idx = 0; idx < balls.length; idx++) {
            int ballX = balls[idx][0];
            int ballY = balls[idx][1];

            int min = Integer.MAX_VALUE;

            // 위쪽 벽 반사
            if (!(startX == ballX && startY < ballY)) {
                min = Math.min(min, getSquareDist(startX, startY, ballX, 2 * n - ballY));
            }

            // 아래쪽 벽 반사
            if (!(startX == ballX && startY > ballY)) {
                min = Math.min(min, getSquareDist(startX, startY, ballX, -ballY));
            }

            // 왼쪽 벽 반사
            if (!(startY == ballY && startX > ballX)) {
                min = Math.min(min, getSquareDist(startX, startY, -ballX, ballY));
            }

            // 오른쪽 벽 반사
            if (!(startY == ballY && startX < ballX)) {
                min = Math.min(min, getSquareDist(startX, startY, 2 * m - ballX, ballY));
            }

            answer[idx] = min;
        }

        return answer;
    }
}