import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    static int answer;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static boolean canProceed(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= M
                || map[row][col] != 0) {
            return false;
        }

        return true;
    }

    static void bfs(List<int[]> ripeList) {
        Queue<int[]> q = new LinkedList<>();
        int day = -1;
        for (int[] arr : ripeList) {
            q.offer(new int[]{arr[0], arr[1]});
        }

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] current = q.poll();
                int curRow = current[0];
                int curCol = current[1];

                for (int delta = 0; delta < 4; delta++) {
                    int nextRow = curRow + deltaRow[delta];
                    int nextCol = curCol + deltaCol[delta];

                    if (canProceed(nextRow, nextCol)) {
                        map[nextRow][nextCol] = 1;
                        q.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
            day++;
        }

        answer = day;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        List<int[]> ripeList = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int val = Integer.parseInt(st.nextToken());

                if (val == 1) {
                    ripeList.add(new int[]{row, col});
                }

                map[row][col] = val;
            }
        }

        bfs(ripeList);

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (map[row][col] == 0) {
                    answer = -1;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
