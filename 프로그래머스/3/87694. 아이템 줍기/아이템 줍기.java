import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int maxX = 0;
        int maxY = 0;

        for(int[] info : rectangle) {
            info[0] *= 2; 
            info[1] *= 2; 
            info[2] *= 2;
            info[3] *= 2;

            maxX = Math.max(maxX, info[2]);
            maxY = Math.max(maxY, info[3]);
        }

        characterX *= 2; 
        characterY *= 2; 
        itemX *= 2; 
        itemY *= 2; 
        int[][] map = new int[maxY + 1][maxX + 1];

        for(int[] info : rectangle) {
            int fromCol = info[0];
            int fromRow = info[1];
            int toCol = info[2];
            int toRow = info[3];

            for(int row = fromRow; row <= toRow; row++) {
                for(int col = fromCol; col <= toCol; col++) {
                    map[row][col] = 1;
                }
            }
        }

        for(int[] info : rectangle) {
            int fromCol = info[0];
            int fromRow = info[1];
            int toCol = info[2];
            int toRow = info[3];

            for(int row = fromRow + 1; row < toRow; row++) {
                for(int col = fromCol + 1; col < toCol; col++) {
                    map[row][col] = 0;
                }
            }
        }

        int[] deltaRow = {-1, 0, 1, 0};
        int[] deltaCol = {0, 1, 0, -1};

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] record = new int[maxY + 1][maxX + 1];

        for(int row = 0; row <= maxY; row++) {
            Arrays.fill(record[row], -1);
        }

        record[characterY][characterX] = 0;
        queue.offer(new int[]{characterY, characterX});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            if(current[0] == itemY && current[1] == itemX)
                return record[itemY][itemX] / 2; 

            for(int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];

                if(nextRow < 0 || nextRow > maxY || nextCol < 0 || nextCol > maxX)
                    continue;

                if(map[nextRow][nextCol] != 1)
                    continue;

                if(record[nextRow][nextCol] != -1)
                    continue;

                record[nextRow][nextCol] = record[current[0]][current[1]] + 1;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }

        return -1;
    }
}