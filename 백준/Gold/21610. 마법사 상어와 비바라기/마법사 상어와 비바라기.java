import java.io.*;
import java.util.*;

/**
 * 1~N 연결 (N -> 1)
 * (N, 1), (N, 2), (N-1, 1), (N-1, 2) 초기 비구름
 * <p>
 * 1. 구름 이동 (←, ↖, ↑, ↗, →, ↘, ↓, ↙)
 * 2. 구름 칸 ++
 * 3. 구름 삭제
 * 4. 2에서 증가 된 칸 대각 물 수만큼 2칸 증가
 * 5. 2 이상인 칸에서 구름 생성(3 해당 X)
 */
public class Main {
    static int N, M;
    static int[][] map;

    static boolean[][] currentCloud;
    static boolean[][] previousCloud;
    static int[] moveDeltaRow = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] moveDeltaCol = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] magicDeltaRow = {-1, -1, 1, 1};
    static int[] magicDeltaCol = {-1, 1, -1, 1};

    static void moveCloud(int direction, int step) {
        boolean[][] nextCloud = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if(!currentCloud[row][col])
                    continue;

                int nextRow = (row + moveDeltaRow[direction] * (step % N) + N) % N;
                int nextCol = (col + moveDeltaCol[direction] * (step % N) + N) % N;

                nextCloud[nextRow][nextCol] = true;
            }
        }

        currentCloud = nextCloud;
    }

    static void rain() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if(currentCloud[row][col])
                    map[row][col]++;
            }
        }
    }

    static void magic() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if(!currentCloud[row][col])
                    continue;

                int count = 0;
                for (int delta = 0; delta < 4; delta++) {
                    int nextRow = row + magicDeltaRow[delta];
                    int nextCol = col + magicDeltaCol[delta];

                    if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                        continue;

                    if(map[nextRow][nextCol] == 0)
                        continue;

                    count++;
                }

                map[row][col] += count;
            }
        }
    }

    static void removeCloud() {
        previousCloud = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (!currentCloud[row][col])
                    continue;
                
                currentCloud[row][col] = false;
                previousCloud[row][col] = true;
            }
        }
    }

    static void makeCloud() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if(map[row][col] < 2 || previousCloud[row][col])
                    continue;

                map[row][col] -= 2;
                currentCloud[row][col] = true;
            }
        }
    }

    static int getSum() {
        int sum = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                sum += map[row][col];
            }
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        currentCloud = new boolean[N][N];
        previousCloud = new boolean[N][N];
        currentCloud[N - 1][0] = true;
        currentCloud[N - 1][1] = true;
        currentCloud[N - 2][0] = true;
        currentCloud[N - 2][1] = true;

        for (int move = 1; move <= M; move++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int step = Integer.parseInt(st.nextToken());

            moveCloud(direction - 1, step);
            rain();
            magic();
            removeCloud();
            makeCloud();
        }

        System.out.println(getSum());
    }
}
