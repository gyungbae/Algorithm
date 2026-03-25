import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[][] map;

    static int[] deltaRow = {0, -1, 0, 1};
    static int[] deltaCol = {1, 0, -1, 0};

    static int[] rotate(int stdRow, int stdCol, int row, int col) {
        int newRow = (col - stdCol) + stdRow;
        int newCol = -(row - stdRow) + stdCol;

        return new int[]{newRow, newCol};
    }

    static void makeCurve(int row, int col, int direction, int level) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{row, col});

        int nextRow = row + deltaRow[direction];
        int nextCol = col + deltaCol[direction];
        list.add(new int[]{nextRow, nextCol});


        while(level-- > 0) {
            int size = list.size();
            int endRow = list.get(size - 1)[0];
            int endCol = list.get(size - 1)[1];

            for(int idx = size - 2; idx >= 0; idx--) {
                int[] pos = list.get(idx);
                int[] rotated = rotate(endRow, endCol, pos[0],pos[1]);
                list.add(rotated);
            }
        }

        for (int[] pos : list) {
            map[pos[0]][pos[1]] = true;
        }
    }

    static int getAnswer() {
        int count = 0;

        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                if(map[row][col] && map[row + 1][col] && map[row][col + 1] && map[row + 1][col + 1])
                    count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new boolean[101][101];

        for (int input = 1; input <= N; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());

            makeCurve(row, col, direction, level);
        }

        System.out.println(getAnswer());
    }
}
