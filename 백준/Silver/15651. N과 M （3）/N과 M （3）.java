import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    static int[] tmpArr;
    static StringBuilder answer = new StringBuilder();

    static void dfs(int depth) {
        if (depth == M) {
            for (int num : tmpArr) {
                answer.append(num).append(" ");
            }
            answer.append("\n");

            return;
        }

        for (int num = 1; num <= N; num++) {
            tmpArr[depth] = num;
            dfs(depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tmpArr = new int[M];
        dfs(0);

        System.out.println(answer);
    }
}
