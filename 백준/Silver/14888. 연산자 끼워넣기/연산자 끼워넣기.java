import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] inputArr;
    static int[] countArr;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static void DFS(int sum, int count0, int count1, int count2, int count3, int depth) {
        if (depth == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int operator = 0; operator < 4; operator++) {
            if (isUsable(operator, count0, count1, count2, count3)) {
                switch (operator) {
                    case 0 -> DFS(sum + inputArr[depth], count0 + 1, count1, count2, count3, depth + 1);
                    case 1 -> DFS(sum - inputArr[depth], count0, count1 + 1, count2, count3, depth + 1);
                    case 2 -> DFS(sum * inputArr[depth], count0, count1, count2 + 1, count3, depth + 1);
                    case 3 -> DFS(sum / inputArr[depth], count0, count1, count2, count3 + 1, depth + 1);
                }
            }
        }
    }

    static boolean isUsable(int operator, int count0, int count1, int count2, int count3) {
        switch (operator) {
            case 0 -> {
                if(countArr[operator] == count0) return false;
                else return true;
            }
            case 1 -> {
                if(countArr[operator] == count1) return false;
                else return true;
            }
            case 2 -> {
                if(countArr[operator] == count2) return false;
                else return true;
            }
            default -> {
                if(countArr[operator] == count3) return false;
                else return true;
            }
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

        DFS(inputArr[0], 0, 0, 0, 0, 1);

        System.out.println(max + "\n" + min);
    }
}
