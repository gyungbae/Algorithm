import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int x = 1;
    static int y = 1;
    static int[][] map;

    static int[] dice = {0, 1, 2, 3, 4, 5, 6};
    static int topIdx = 1;
    static int upIdx = 2;
    static int rightIdx = 3;
    static int downIdx = 5;
    static int leftIdx = 4;
    static int bottomIdx = 6;
    static int direction = 0;
    static int[] deltaRow = {0, 1, 0, -1};
    static int[] deltaCol = {1, 0, -1, 0};

    static void move() {
        int nextRow = x + deltaRow[direction];
        int nextCol = y + deltaCol[direction];

        if (nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > M) {
            direction = (direction + 2) % 4;
            nextRow = x + deltaRow[direction];
            nextCol = y + deltaCol[direction];
        }

        switch (direction) {
            case 0 -> rollEast();
            case 1 -> rollSouth();
            case 2 -> rollWest();
            case 3 -> rollNorth();
        }

        x = nextRow;
        y = nextCol;

        if (dice[bottomIdx] > map[x][y]) {
            turnRight();
        } else if(dice[bottomIdx] < map[x][y]){
            turnLeft();
        }
    }

    static void rollEast() {
        int tmp = topIdx;
        topIdx = leftIdx;
        leftIdx = bottomIdx;
        bottomIdx = rightIdx;
        rightIdx = tmp;
    }

    static void rollWest() {
        int tmp = topIdx;
        topIdx = rightIdx;
        rightIdx = bottomIdx;
        bottomIdx = leftIdx;
        leftIdx = tmp;
    }

    static void rollNorth() {
        int tmp = topIdx;
        topIdx = downIdx;
        downIdx = bottomIdx;
        bottomIdx = upIdx;
        upIdx = tmp;
    }

    static void rollSouth() {
        int tmp = topIdx;
        topIdx = upIdx;
        upIdx = bottomIdx;
        bottomIdx = downIdx;
        downIdx = tmp;
    }

    static void turnRight() {
        direction = (direction + 1) % 4;
    }

    static void turnLeft() {
        direction = (direction + 3) % 4;
    }

    static int getScore() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][M + 1];

        int score = map[x][y];
        visited[x][y] = true;
        queue.offer(new int[]{x, y});

        int count = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            count++;

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];

                if(nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > M)
                    continue;

                if(visited[nextRow][nextCol] || map[nextRow][nextCol] != score)
                    continue;

                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }

        return score * count;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        while (K-- > 0) {
            move();
            sum += getScore();
        }

        System.out.println(sum);
    }
}
