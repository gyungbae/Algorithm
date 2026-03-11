import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int x = 1;
    static int y = 1;
    static int[][] map;

    static Dice dice = new Dice();
    static int[] deltaRow = {0, 1, 0, -1};
    static int[] deltaCol = {1, 0, -1, 0};

    static void move() {
        int nextRow = x + deltaRow[dice.direction];
        int nextCol = y + deltaCol[dice.direction];

        if (nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > M) {
            dice.direction = (dice.direction + 2) % 4;
            nextRow = x + deltaRow[dice.direction];
            nextCol = y + deltaCol[dice.direction];
        }

        switch (dice.direction) {
            case 0 -> dice.rollEast();
            case 1 -> dice.rollSouth();
            case 2 -> dice.rollWest();
            case 3 -> dice.rollNorth();
        }

        x = nextRow;
        y = nextCol;

        if (dice.values[dice.bottomIdx] > map[x][y]) {
            dice.turnRight();
        } else if(dice.values[dice.bottomIdx] < map[x][y]){
            dice.turnLeft();
        }
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

class Dice {
    int direction = 0;
    
    int[] values = {0, 1, 2, 3, 4, 5, 6};
    int topIdx = 1;
    int upIdx = 2;
    int rightIdx = 3;
    int leftIdx = 4;
    int downIdx = 5;
    int bottomIdx = 6;

    void rollEast() {
        int tmp = topIdx;
        topIdx = leftIdx;
        leftIdx = bottomIdx;
        bottomIdx = rightIdx;
        rightIdx = tmp;
    }

    void rollWest() {
        int tmp = topIdx;
        topIdx = rightIdx;
        rightIdx = bottomIdx;
        bottomIdx = leftIdx;
        leftIdx = tmp;
    }

    void rollNorth() {
        int tmp = topIdx;
        topIdx = downIdx;
        downIdx = bottomIdx;
        bottomIdx = upIdx;
        upIdx = tmp;
    }

    void rollSouth() {
        int tmp = topIdx;
        topIdx = upIdx;
        upIdx = bottomIdx;
        bottomIdx = downIdx;
        downIdx = tmp;
    }

    void turnRight() {
        direction = (direction + 1) % 4;
    }

    void turnLeft() {
        direction = (direction + 3) % 4;
    }
}
