import java.io.*;
import java.util.*;

/**
 * 1, N 행 열 연결(N -> 1) 구름 이동
 * (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 구름 생성
 * 1. 이동 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
 * 2. 구름 위치 ++
 * 3. 구름 제거
 * 4. 대각선 거리 물 양 증가(경계 넘어서는 칸은 제외)
 * 5. 2 이상 칸 구름 생성, -= 2, 3.에서 삭제된 위치 제외
 * 모든 이동 후 물 양 합 출력
 */
public class Main {
    static int N, M;
    static int[][] map;

    static List<int[]> prevClouds = new ArrayList<>();
    static List<int[]> curClouds = new ArrayList<>();
    static int[] moveDeltaRow = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] moveDeltaCol = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] magicDeltaRow = {-1, -1, 1, 1};
    static int[] magicDeltaCol = {-1, 1, -1, 1};

    static void move(int direction, int step) {
        for (int[] info : prevClouds) {
            int nextRow = (info[0] + moveDeltaRow[direction] * step) % N;
            int nextCol = (info[1] + moveDeltaCol[direction] * step) % N;

            if (nextRow < 0) nextRow += N;
            if (nextCol < 0) nextCol += N;

            curClouds.add(new int[]{nextRow, nextCol});
        }
    }

    static void rain() {
        for (int[] info : curClouds) {
            map[info[0]][info[1]]++;
        }

        prevClouds = new ArrayList<>(curClouds);
        curClouds = new ArrayList<>();
    }

    static void magic() {
        for (int[] info : prevClouds) {
            int row = info[0];
            int col = info[1];

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

    static void makeClouds() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (map[row][col] < 2)
                    continue;

                boolean isPrev = false;
                for (int[] info : prevClouds) {
                    if (info[0] == row && info[1] == col) {
                        isPrev = true;
                        break;
                    }
                }

                if (!isPrev) {
                    curClouds.add(new int[]{row, col});
                    map[row][col] -= 2;
                }
            }
        }

        prevClouds = new ArrayList<>(curClouds);
        curClouds = new ArrayList<>();
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

        prevClouds.add(new int[]{N - 1, 0});
        prevClouds.add(new int[]{N - 1, 1});
        prevClouds.add(new int[]{N - 2, 0});
        prevClouds.add(new int[]{N - 2, 1});

        for (int input = 1; input <= M; input++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken()) - 1;
            int step = Integer.parseInt(st.nextToken());

            move(direction, step);
            rain();
            magic();
            makeClouds();
        }

        System.out.println(getSum());
    }
}
