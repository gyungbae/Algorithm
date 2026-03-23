import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static int[][] map;
    static int answer = 4;

    static void fix(int startIdx, int depth) {
        if (isFixed()) {
            answer = Math.min(answer, depth);
            return;
        }

        if (depth == 3 || depth >= answer)
            return;

        for (int idx = startIdx; idx < H * N; idx++) {
            int row = idx / N;
            int col = idx % N;

            if (map[row][col] == 1)
                continue;
            if (col - 1 >= 0 && map[row][col - 1] == 1)
                continue;
            if (col + 1 < N - 1 && map[row][col + 1] == 1)
                continue;

            map[row][col] = 1;
            fix(idx + 1, depth + 1);
            map[row][col] = 0;
        }
    }

    static boolean isFixed() {
        for (int start = 0; start < N; start++) {
            int line = start;

            for (int row = 0; row < H; row++) {
                if (line - 1 >= 0 && map[row][line - 1] == 1) {
                    line--;
                } else if (line + 1 <= N && map[row][line] == 1) {
                    line++;
                }
            }

            if (line != start)
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N];

        for (int input = 0; input < M; input++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            map[row][col] = 1;
        }

        fix(0, 0);

        System.out.println(answer == 4 ? -1 : answer);
    }
}
