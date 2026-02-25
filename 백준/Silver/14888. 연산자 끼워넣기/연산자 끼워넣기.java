import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] inputArr;
    static int[] countArr;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static void DFS(int sum, int depth) {
        if (depth == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }
        
        int next = inputArr[depth];
        if (countArr[0] > 0) {
            countArr[0]--;
            DFS(sum + next, depth + 1);
            countArr[0]++;
        }

        if (countArr[1] > 0) {
            countArr[1]--;
            DFS(sum - next, depth + 1);
            countArr[1]++;
        }

        if (countArr[2] > 0) {
            countArr[2]--;
            DFS(sum * next, depth + 1);
            countArr[2]++;
        }

        if (countArr[3] > 0) {
            countArr[3]--;
            DFS(sum / next, depth + 1);
            countArr[3]++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        inputArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            inputArr[idx] = Integer.parseInt(st.nextToken());
        }

        countArr = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < 4; idx++) {
            countArr[idx] = Integer.parseInt(st.nextToken());
        }

        DFS(inputArr[0], 1);

        System.out.println(max + "\n" + min);
    }
}
