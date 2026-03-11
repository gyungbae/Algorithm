import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x, y, K;
    static int[][] map;

    static int[] dice = new int[7];
    static int topIdx = 1;
    static int upIdx = 2;
    static int rightIdx = 3;
    static int downIdx = 5;
    static int leftIdx = 4;
    static int bottomIdx = 6;
    static int[] deltaRow = {0, 0, 0, -1, 1};
    static int[] deltaCol = {0, 1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder();

    static void move(int delta) {
        int nextRow = x + deltaRow[delta];
        int nextCol = y + deltaCol[delta];

        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M)
            return;

        switch (delta) {
            case 1 -> rollEast();
            case 2 -> rollWest();
            case 3 -> rollNorth();
            case 4 -> rollSouth();
        }

        x = nextRow;
        y = nextCol;

        if (map[x][y] == 0) {
            map[x][y] = dice[bottomIdx];
        } else {
            dice[bottomIdx] = map[x][y];
            map[x][y] = 0;
        }

        sb.append(dice[topIdx]).append("\n");
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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            move(Integer.parseInt(st.nextToken()));
        }

        System.out.println(sb);
    }
}
