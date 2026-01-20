import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    static List<int[]> virusList = new ArrayList<>();
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static boolean[][] visited;
    static int answer;

    static void buildWall(int depth) {
        if (depth == 3) {
            int[][] tmpMap = new int[N][M];
            for (int row = 0; row < N; row++) {
                tmpMap[row] = Arrays.copyOf(map[row], M);
            }

            spread(tmpMap);
            answer = Math.max(answer, count(tmpMap));
            return;
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (map[row][col] == 0) {
                    map[row][col] = 1;
                    buildWall(depth + 1);
                    map[row][col] = 0;
                }
            }
        }
    }

    static void spread(int[][] map) {
        Queue<int[]> q = new ArrayDeque<>();

        for (int[] virus : virusList) {
            q.offer(virus);
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];
                if (canProceed(map, nextRow, nextCol)) {
                    map[nextRow][nextCol] = 2;
                    q.offer(new int[]{nextRow, nextCol});
                }
            }
        }
    }

    static int count(int[][] map) {
        int cnt = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (map[row][col] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static boolean canProceed(int[][] map, int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= M) {
            return false;
        }

        if (map[row][col] != 0) {
            return false;
        }

        return true;
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
                if (num == 2) {
                    virusList.add(new int[]{row, col});
                }
            }
        }

        buildWall(0);
        
        System.out.println(answer);
    }
}
