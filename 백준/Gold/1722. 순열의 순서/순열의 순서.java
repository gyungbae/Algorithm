import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int command;
    static long k;
    static int[] inputArr;

    static long[] factorialArr;
    static boolean[] selected;
    static StringBuilder sb = new StringBuilder();

    static void searchPermutation() {
        int[] permutationArr = new int[N + 1];

        for (int permutationIdx = 1; permutationIdx <= N; permutationIdx++) {
            long nextPossibleCount = factorialArr[N - permutationIdx];

            for (int num = 1; num <= N; num++) {
                if (selected[num]) {
                    continue;
                }

                if (k > nextPossibleCount) {
                    k -= nextPossibleCount;
                    continue;
                }

                selected[num] = true;
                permutationArr[permutationIdx] = num;
                break;
            }
        }

        for (int idx = 1; idx <= N; idx++) {
            sb.append(permutationArr[idx]).append(" ");
        }
    }

    static void searchOrder() {
        long order = 1;
        for (int inputIdx = 1; inputIdx <= N; inputIdx++) {
            long nextPossibleCount = factorialArr[N - inputIdx];

            int currentNum = inputArr[inputIdx];
            for (int num = 1; num < currentNum; num++) {
                if (selected[num]) {
                    continue;
                }

                order += nextPossibleCount;
            }

            selected[currentNum] = true;
        }

        sb.append(order);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        factorialArr = new long[N + 1];
        factorialArr[0] = 1;
        for (int idx = 1; idx <= N; idx++) {
            factorialArr[idx] = factorialArr[idx - 1] * idx;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        command = Integer.parseInt(st.nextToken());
        selected = new boolean[N + 1];
        if (command == 1) {
            k = Long.parseLong(st.nextToken());
            searchPermutation();
        } else {
            inputArr = new int[N + 1];
            for (int idx = 1; idx <= N; idx++) {
                inputArr[idx] = Integer.parseInt(st.nextToken());
            }
            searchOrder();
        }

        System.out.println(sb);
    }
}
