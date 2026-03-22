import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    static List<int[]> cctvList = new ArrayList<>();
    static int cctvCnt;
    static int zeroCnt;
    static boolean[][] watched;
    static int answer = Integer.MAX_VALUE;

    static int[] deltaRow = {-1, 0, 1, 0};
    static int[] deltaCol = {0, 1, 0, -1};

    static int[][][] cctvDirections = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}, {0, 2}, {1, 3}},
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
            {{0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}}
    };

    static boolean isInRange(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < M;
    }

    static void setDirection(List<Integer> directionList) {
        if (directionList.size() == cctvCnt) {
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
            int direction = directionList.get(idx);

            count += watchDirections(row, col, cctvDirections[type][direction]);
        }

        return count;
    }

    static int watchDirections(int row, int col, int[] directions) {
        int count = 0;
        for (int direction : directions) {
            count += watchOneDirection(row, col, direction);
        }
        return count;
    }

    static int watchOneDirection(int row, int col, int direction) {
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

                if (num != 0 && num != 6)
                    cctvList.add(new int[]{row, col, num});

                if (num == 0)
                    zeroCnt++;
            }
        }

        cctvCnt = cctvList.size();
        setDirection(new ArrayList<>());

        System.out.println(answer);
    }
}
