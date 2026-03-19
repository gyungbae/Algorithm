import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    static List<int[]> virusList = new ArrayList<>();
    static int zeroCount;
    static boolean[][] activated;
    static int[] deltaCol = {0, 0, -1, 1};
    static int[] deltaRow = {-1, 1, 0, 0};
    static boolean[][] visited;
    static int answer = Integer.MAX_VALUE;

    static void activate(int startIdx, int depth) {
        if(depth == M) {
            int[][] tmpMap = new int[N][N];
            for (int row = 0; row < N; row++) {
                tmpMap[row] = Arrays.copyOf(map[row], N);
            }

            int second = spread(tmpMap);
            answer = Math.min(answer, second);

            return;
        }

        for(int idx = startIdx; idx < N * N; idx++) {
            int row = idx / N;
            int col = idx % N;

            if(map[row][col] != 2)
                continue;

            activated[row][col] = true;
            activate(idx + 1, depth + 1);
            activated[row][col] = false;
        }
    }

    static int spread(int[][] tmpMap) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[N][N];

        for (int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(!activated[row][col])
                    continue;

                visited[row][col] = true;
                queue.offer(new int[]{row, col});
            }
        }

        int tmpZeroCount = zeroCount;
        int second = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            second++;

            while (size-- > 0) {
                int[] current = queue.poll();

                for (int delta = 0; delta < 4; delta++) {
                    int row = current[0] + deltaRow[delta];
                    int col = current[1] + deltaCol[delta];

                    if(row < 0 || row >= N || col < 0 || col >= N)
                        continue;

                    if(visited[row][col] || map[row][col] == 1)
                        continue;

                    if(map[row][col] == 0) {
                        tmpMap[row][col] = 2;
                        tmpZeroCount--;

                        if(tmpZeroCount == 0)
                            return second;
                    }

                    visited[row][col] = true;
                    queue.offer(new int[]{row, col});
                }
            }


        }

        second = Integer.MAX_VALUE;
        return second;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int num = Integer.parseInt(st.nextToken());
                map[row][col] = num;

                if(num == 2)
                    virusList.add(new int[]{row, col});

                if(num == 0)
                    zeroCount++;
            }
        }

        if(zeroCount == 0) {
            System.out.println(0);
            return;
        }

        activated = new boolean[N][N];
        activate(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
