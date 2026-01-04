import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static boolean[] selected;

    static void dfs(int curNum, String str) {
        if (str.length() == M) {
            for (int idx = 0; idx < str.length(); idx++) {
                sb.append(str.charAt(idx)).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int num = curNum; num <= N; num++) {
            dfs(num, str + num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(1, "");

        System.out.println(sb);
    }
}
