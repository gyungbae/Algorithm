import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static int[][] map;

    static int size;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();

    static boolean isInRange(int row, int col) {
        if(row < 0 || row >= size || col < 0 || col >= size)
            return false;

        return true;
    }

    static void fireStorm(int level) {
        int target = (int) Math.pow(2, level);
        rotate(0, 0, size, target);
        melt();
    }

    static void rotate(int row, int col, int size, int target) {
        if (size == target) {
            int[][] tmp = new int[size][size];
            for (int tmpRow = 0; tmpRow < size; tmpRow++) {
                for (int tmpCol = 0; tmpCol < size; tmpCol++) {
                    tmp[tmpRow][tmpCol] = map[row + (size - 1 - tmpCol)][col + tmpRow];
                }
            }

            for (int tmpRow = 0; tmpRow < size; tmpRow++) {
                for (int tmpCol = 0; tmpCol < size; tmpCol++) {
                    map[row + tmpRow][col + tmpCol] = tmp[tmpRow][tmpCol];
                }
            }

            return;
        }

        int half = size / 2;
        rotate(row, col, half, target);
        rotate(row, col + half, half, target);
        rotate(row + half, col, half, target);
        rotate(row + half, col + half, half, target);
    }

    static void melt() {
        List<int[]> meltList = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(map[row][col] == 0)
                    continue;

                int count = 0;
                for (int delta = 0; delta < 4; delta++) {
                    int nextRow = row + deltaRow[delta];
                    int nextCol = col + deltaCol[delta];

                    if(!isInRange(nextRow, nextCol) || map[nextRow][nextCol] == 0)
                        continue;

                    count++;
                }

                if(count < 3)
                    meltList.add(new int[]{row, col});
            }
        }

        for (int[] info : meltList) {
            map[info[0]][info[1]]--;
        }
    }

    static void getAnswer() {
        int sum = 0;
        int max = 0;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sum += map[row][col];

                if (!visited[row][col] && map[row][col] > 0)
                    max = Math.max(max, getArea(row, col));
            }
        }

        sb.append(sum).append("\n").append(max);
    }

    static int getArea(int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();

        visited[row][col] = true;
        queue.offer(new int[]{row, col});

        int area = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            area++;

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];

                if(!isInRange(nextRow, nextCol) || visited[nextRow][nextCol] || map[nextRow][nextCol] == 0)
                    continue;

                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }

        return area;
    }

     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, N);
        map = new int[size][size];
        for (int row = 0; row < size; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < size; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[size][size];
        st = new StringTokenizer(br.readLine());
        for (int input = 1; input <= Q; input++) {
            fireStorm(Integer.parseInt(st.nextToken()));
        }

        getAnswer();

        System.out.println(sb);
    }
}
