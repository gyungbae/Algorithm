class Solution {
    int[] deltaRow = {1, 0, 0, -1};
    int[] deltaCol = {0, -1, 1, 0};
    char[] dirArr = {'d', 'l', 'r', 'u'};

    int n;
    int m;
    int targetRow;
    int targetCol;

    String answer = "impossible";
    boolean found;

    void dfs(int row, int col, int remainMove, StringBuilder path) {
        if(found)
            return;

        int distance = Math.abs(row - targetRow) + Math.abs(col - targetCol);

        if(distance > remainMove)
            return;

        if((remainMove - distance) % 2 == 1)
            return;

        if(remainMove == 0) {
            if(row == targetRow && col == targetCol) {
                answer = path.toString();
                found = true;
            }
            return;
        }

        for(int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
            int nextRow = row + deltaRow[deltaIdx];
            int nextCol = col + deltaCol[deltaIdx];

            if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m)
                continue;

            path.append(dirArr[deltaIdx]);

            dfs(nextRow, nextCol, remainMove - 1, path);

            path.deleteCharAt(path.length() - 1);
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.targetRow = r - 1;
        this.targetCol = c - 1;

        int startRow = x - 1;
        int startCol = y - 1;

        int distance = Math.abs(startRow - targetRow)
                + Math.abs(startCol - targetCol);

        if(distance > k)
            return "impossible";

        if((k - distance) % 2 == 1)
            return "impossible";

        dfs(startRow, startCol, k, new StringBuilder());

        return answer;
    }
}