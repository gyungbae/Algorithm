import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    static List<int[]> cctvList = new ArrayList<>();
    static int cctvCnt;
    static int zeroCnt;
    static int[] deltaRow = {-1, 0, 1, 0};
    static int[] deltaCol = {0, 1, 0, -1};
    static boolean[][] watched;
    static int answer = Integer.MAX_VALUE;

    static boolean isInRange(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < M;
    }

    static void setDirection (List<Integer> directionList) {
        if(directionList.size() == cctvCnt) {
            int result = zeroCnt - watch(directionList);
            answer = Math.min(answer, result);
            return;
        }

        for (int direction = 0; direction < 4; direction++) {
            directionList.add(direction);
            setDirection(directionList);
            directionList.remove(directionList.size() - 1);
        }
    }

    static int watch(List<Integer> directionList) {
        watched = new boolean[N][M];
        int count = 0;
        for (int idx = 0; idx < cctvCnt; idx++) {
            int[] cctvInfo = cctvList.get(idx);
            int row = cctvInfo[0];
            int col = cctvInfo[1];
            int type = cctvInfo[2];
            int delta = directionList.get(idx);

            switch (type) {
                case 1 -> count += cctv1(row, col, delta);
                case 2 -> count += cctv2(row, col, delta);
                case 3 -> count += cctv3(row, col, delta);
                case 4 -> count += cctv4(row, col, delta);
                case 5 -> count += cctv5(row, col);
            }
        }

        return count;
    }

    static int cctv1(int row, int col, int direction) {
        int count = 0;

        int nextRow = row;
        int nextCol = col;
        while (true) {
            nextRow += deltaRow[direction];
            nextCol += deltaCol[direction];

            if(!isInRange(nextRow, nextCol) || map[nextRow][nextCol] == 6)
                break;

            if (!watched[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                watched[nextRow][nextCol] = true;
                count++;
            }
        }

        return count;
    }

    static int cctv2(int row, int col, int direction) {
        int count = 0;

        int nextRow = row;
        int nextCol = col;
        while (true) {
            nextRow += deltaRow[direction];
            nextCol += deltaCol[direction];

            if (!isInRange(nextRow, nextCol) || map[nextRow][nextCol] == 6)
                break;

            if (!watched[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                watched[nextRow][nextCol] = true;
                count++;
            }
        }

        direction = (direction + 2) % 4;

        nextRow = row;
        nextCol = col;
        while (true) {
            nextRow += deltaRow[direction];
            nextCol += deltaCol[direction];

            if (!isInRange(nextRow, nextCol) || map[nextRow][nextCol] == 6)
                break;

            if (!watched[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                watched[nextRow][nextCol] = true;
                count++;
            }
        }

        return count;
    }

    static int cctv3(int row, int col, int direction) {
        int count = 0;

        int nextRow = row;
        int nextCol = col;
        while (true) {
            nextRow += deltaRow[direction];
            nextCol += deltaCol[direction];

            if (!isInRange(nextRow, nextCol) || map[nextRow][nextCol] == 6)
                break;

            if (!watched[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                watched[nextRow][nextCol] = true;
                count++;
            }
        }

        direction = (direction + 1) % 4;

        nextRow = row;
        nextCol = col;
        while (true) {
            nextRow += deltaRow[direction];
            nextCol += deltaCol[direction];

            if (!isInRange(nextRow, nextCol) || map[nextRow][nextCol] == 6)
                break;

            if (!watched[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                watched[nextRow][nextCol] = true;
                count++;
            }
        }

        return count;
    }

    static int cctv4(int row, int col, int direction) {
        int count = 0;
        for (int delta = 0; delta < 4; delta++) {
            if(delta == direction)
                continue;

            int nextRow = row;
            int nextCol = col;
            while (true) {
                nextRow += deltaRow[delta];
                nextCol += deltaCol[delta];

                if(!isInRange(nextRow, nextCol) || map[nextRow][nextCol] == 6)
                    break;

                if (!watched[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                    watched[nextRow][nextCol] = true;
                    count++;
                }
            }
        }

        return count;
    }

    static int cctv5(int row, int col) {
        int count = 0;
        for (int delta = 0; delta < 4; delta++) {
            int nextRow = row;
            int nextCol = col;
            while (true) {
                nextRow += deltaRow[delta];
                nextCol += deltaCol[delta];

                if(!isInRange(nextRow, nextCol) || map[nextRow][nextCol] == 6)
                    break;

                if (!watched[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                    watched[nextRow][nextCol] = true;
                    count++;
                }
            }
        }

        return count;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int num = Integer.parseInt(st.nextToken());
                map[row][col] = num;

                if(num != 0 && num != 6)
                    cctvList.add(new int[]{row, col, num});

                if(num == 0)
                    zeroCnt++;
            }
        }

        cctvCnt = cctvList.size();
        setDirection(new ArrayList<>());

        System.out.println(answer);
    }
}
