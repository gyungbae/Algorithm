import java.io.*;
import java.util.*;

public class Main {
    static int N, M, fuel;
    static int[][] map;
    static int startRow, startCol;
    static int[][] infoArr;
    static boolean[] completed;

    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static boolean isInRange(int row, int col) {
        if(row < 1 || row > N || col < 1 || col > N)
            return false;

        return true;
    }

    static int[] findNearest() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];

        visited[startRow][startCol] = true;
        queue.offer(new int[]{startRow, startCol, 0});

        int minDistance = Integer.MAX_VALUE;
        int nearestIdx = -1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            int distance = current[2];

            if(distance > minDistance)
                break;

            for (int idx = 0; idx < M; idx++) {
                if(completed[idx])
                    continue;

                int[] info = infoArr[idx];

                if(info[0] != currentRow || info[1] != currentCol)
                    continue;

                if (distance < minDistance) {
                    minDistance = distance;
                    nearestIdx = idx;
                }

                else if (distance == minDistance) {
                    if (info[0] < infoArr[nearestIdx][0]) {
                        nearestIdx = idx;
                    } else if (info[0] == infoArr[nearestIdx][0] && info[1] < infoArr[nearestIdx][1]) {
                        nearestIdx = idx;
                    }
                }
            }

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = currentRow + deltaRow[delta];
                int nextCol = currentCol + deltaCol[delta];

                if(!isInRange(nextRow, nextCol) || visited[nextRow][nextCol] || map[nextRow][nextCol] == 1)
                    continue;

                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol, distance + 1});
            }
        }

        if(nearestIdx == -1)
            return null;

        int[] nearestInfo = Arrays.copyOf(infoArr[nearestIdx], 6);
        nearestInfo[4] = nearestIdx;
        nearestInfo[5] = minDistance;
        return nearestInfo;
    }

    static int move(int[] info) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];

        visited[startRow][startCol] = true;
        queue.offer(new int[]{startRow, startCol, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            int distance = current[2];

            if (currentRow == info[2] && currentCol == info[3])
                return distance;

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = currentRow + deltaRow[delta];
                int nextCol = currentCol + deltaCol[delta];

                if (!isInRange(nextRow, nextCol) || visited[nextRow][nextCol] || map[nextRow][nextCol] == 1)
                    continue;

                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol, distance + 1});
            }
        }

        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        startRow = Integer.parseInt(st.nextToken());
        startCol = Integer.parseInt(st.nextToken());

        infoArr = new int[M][4];
        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 4; col++) {
                infoArr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        completed = new boolean[M];
        int completeCount = 0;
        while (true) {
            int[] info = findNearest();
            if (info == null) {
                System.out.println(-1);
                return;
            }

            fuel -= info[5];
            if (fuel < 0) {
                System.out.println(-1);
                return;
            }

            startRow = info[0];
            startCol = info[1];
            int distance = move(info);
            if (distance == -1) {
                System.out.println(-1);
                return;
            }

            fuel -= distance;
            if (fuel < 0) {
                System.out.println(-1);
                return;
            }

            fuel += distance * 2;
            startRow = info[2];
            startCol = info[3];
            completed[info[4]] = true;
            if(++completeCount == M)
                break;
        }

        System.out.println(fuel);
    }
}
