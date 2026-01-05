import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    static int[] tmpArr;
    static boolean[] selected;
    static StringBuilder answer = new StringBuilder();

    static void dfs(int curNum, int depth) {
        if (depth == M) {
            for (int num : tmpArr) {
                answer.append(num).append(" ");
            }
            answer.append("\n");

            return;
        }

        for (int num = curNum; num <= N; num++) {
            if (selected[num]) {
                continue;
            }

            selected[num] = true;
            tmpArr[depth] = num;
            dfs(num, depth + 1);
            selected[num] = false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tmpArr = new int[M];
        selected = new boolean[N + 1];
        dfs(1, 0);

        System.out.println(answer);
    }
}
