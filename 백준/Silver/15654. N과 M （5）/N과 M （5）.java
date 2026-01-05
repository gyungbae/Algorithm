import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] numArr;

    static boolean[] selected;
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

        for (int idx = 0; idx < N; idx++) {
            if (selected[idx]) {
                continue;
            }

            selected[idx] = true;
            tmpArr[depth] = numArr[idx];
            dfs(depth + 1);
            selected[idx] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            numArr[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numArr);

        selected = new boolean[N];
        tmpArr = new int[M];
        dfs(0);

        System.out.println(answer);
    }
}
