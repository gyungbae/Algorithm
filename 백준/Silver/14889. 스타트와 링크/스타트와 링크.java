import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] inputArr;

    static boolean[] selected;
    static int answer = Integer.MAX_VALUE;

    static void DFS(int startIdx, int depth) {
        if (depth == N / 2) {
            int sum1 = 0;
            int sum2 = 0;
            for (int idx1 = 1; idx1 <= N; idx1++) {
                for (int idx2 = 1; idx2 <= N; idx2++) {
                    if(selected[idx1] && selected[idx2]) sum1 += inputArr[idx1][idx2];
                    if(!selected[idx1] && !selected[idx2]) sum2 += inputArr[idx1][idx2];
                }
            }

            answer = Math.min(answer, Math.abs(sum1 - sum2));
            return;
        }

        for (int idx = startIdx; idx <= N; idx++) {
            selected[idx] = true;
            DFS(idx + 1, depth + 1);
            selected[idx] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        inputArr = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                inputArr[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        selected = new boolean[N + 1];
        selected[1] = true;
        DFS(2, 1);

        System.out.println(answer);
    }
}
