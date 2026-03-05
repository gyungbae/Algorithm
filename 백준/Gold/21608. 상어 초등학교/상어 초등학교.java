import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] seat;                 // 자리 배치
    static int[][] like;                 // like[학생] = 좋아하는 4명
    static boolean[][] isLike;           // isLike[a][b] = a가 b를 좋아함
    static int[] order;                  // 배치 순서

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static boolean inRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static void place(int student) {
        int bestR = -1, bestC = -1;
        int bestLikeCnt = -1;
        int bestBlankCnt = -1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (seat[r][c] != 0) continue;

                int likeCnt = 0;
                int blankCnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (!inRange(nr, nc)) continue;

                    if (seat[nr][nc] == 0) blankCnt++;
                    else if (isLike[student][seat[nr][nc]]) likeCnt++;
                }

                // 1) likeCnt 큰
                // 2) blankCnt 큰
                // 3) r 작은
                // 4) c 작은
                if (likeCnt > bestLikeCnt
                        || (likeCnt == bestLikeCnt && blankCnt > bestBlankCnt)
                        || (likeCnt == bestLikeCnt && blankCnt == bestBlankCnt && r < bestR)
                        || (likeCnt == bestLikeCnt && blankCnt == bestBlankCnt && r == bestR && c < bestC)) {
                    bestLikeCnt = likeCnt;
                    bestBlankCnt = blankCnt;
                    bestR = r;
                    bestC = c;
                }
            }
        }

        seat[bestR][bestC] = student;
    }

    static int satisfaction() {
        int sum = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int student = seat[r][c];
                int likeCnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (!inRange(nr, nc)) continue;

                    if (isLike[student][seat[nr][nc]]) likeCnt++;
                }

                if (likeCnt == 1) sum += 1;
                else if (likeCnt == 2) sum += 10;
                else if (likeCnt == 3) sum += 100;
                else if (likeCnt == 4) sum += 1000;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int total = N * N;
        seat = new int[N][N];
        like = new int[total + 1][4];
        isLike = new boolean[total + 1][total + 1];
        order = new int[total];

        for (int i = 0; i < total; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            order[i] = s;
            for (int k = 0; k < 4; k++) {
                int f = Integer.parseInt(st.nextToken());
                like[s][k] = f;
                isLike[s][f] = true;
            }
        }

        for (int i = 0; i < total; i++) {
            place(order[i]);
        }

        System.out.println(satisfaction());
    }
}