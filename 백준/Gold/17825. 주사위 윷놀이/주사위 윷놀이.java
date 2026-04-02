import java.io.*;
import java.util.*;

public class Main {
    static int[] dice = new int[10];
    static int[] piece = new int[4];
    static int answer = 0;

    static int[] score = new int[33];
    static int[] nextIdx = new int[33];
    static int[] blue = new int[33];

    static void init() {
        for (int idx = 0; idx <= 20; idx++) {
            score[idx] = idx * 2;
        }
        for (int idx = 0; idx < 20; idx++) {
            nextIdx[idx] = idx + 1;
        }
        nextIdx[20] = 32;
        nextIdx[32] = 32;

        Arrays.fill(blue, -1);
        blue[5] = 21;
        blue[10] = 24;
        blue[15] = 26;

        score[21] = 13; score[22] = 16; score[23] = 19;
        nextIdx[21] = 22; nextIdx[22] = 23; nextIdx[23] = 29;

        score[24] = 22; score[25] = 24;
        nextIdx[24] = 25; nextIdx[25] = 29;

        score[26] = 28; score[27] = 27; score[28] = 26;
        nextIdx[26] = 27; nextIdx[27] = 28; nextIdx[28] = 29;

        score[29] = 25; score[30] = 30; score[31] = 35;
        nextIdx[29] = 30; nextIdx[30] = 31; nextIdx[31] = 20;
    }

    static int move(int prev, int step) {
        int next = prev;

        if (next == 32)
            return 32;

        if (blue[next] != -1) {
            next = blue[next];
            step--;
        } else {
            next = nextIdx[next];
            step--;
        }

        while (step-- > 0 && next != 32) {
            next = nextIdx[next];
        }

        return next;
    }

    static void dfs(int depth, int sum) {
        if (depth == 10) {
            answer = Math.max(answer, sum);
            return;
        }

        int step = dice[depth];

        for (int idx = 0; idx < 4; idx++) {
            int prev = piece[idx];
            
            if (prev == 32)
                continue;

            int next = move(prev, step);

            boolean canMove = true;
            if (next != 32) {
                for (int checkIdx = 0; checkIdx < 4; checkIdx++) {
                    if (idx != checkIdx && piece[checkIdx] == next) {
                        canMove = false;
                        break;
                    }
                }
            }

            if (!canMove) continue;

            piece[idx] = next;
            dfs(depth + 1, sum + score[next]);
            piece[idx] = prev;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int idx = 0; idx < 10; idx++) {
            dice[idx] = Integer.parseInt(st.nextToken());
        }

        init();
        dfs(0, 0);

        System.out.println(answer);
    }
}
