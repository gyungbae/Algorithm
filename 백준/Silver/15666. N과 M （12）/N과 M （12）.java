import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] numArr;

    static int[] tmpArr;
    static boolean[] selected;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int curIdx, int depth) {
        if (depth == M) {
            for (int num : tmpArr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");

            return;
        }

        int prevNum = Integer.MAX_VALUE;
        for (int idx = curIdx; idx < N; idx++) {
            if (numArr[idx] == prevNum) {
                continue;
            }

            tmpArr[depth] = numArr[idx];
            prevNum = numArr[idx];
            dfs(idx, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        numArr = new int[N];
        for (int idx = 0; idx < N; idx++) {
            numArr[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numArr);

        tmpArr = new int[M];
        selected = new boolean[N];
        dfs(0, 0);

        System.out.println(sb);
    }
}
