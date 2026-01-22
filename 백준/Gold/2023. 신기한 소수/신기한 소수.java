import java.io.*;

public class Main {
    static int N;

    static int[] startNumArr = {2, 3, 5, 7};
    static int[] rearNumArr = {1, 3, 7, 9};

    static StringBuilder sb = new StringBuilder();

    static void DFS(int curNum, int depth) {
        if (depth == N) {
            sb.append(curNum).append("\n");
            return;
        }

        for (int num : rearNumArr) {
            int nextNum = curNum * 10 + num;
            if (isPrime(nextNum)) {
                DFS(nextNum, depth + 1);
            }
        }
    }

    static boolean isPrime(int num) {
        for (int divide = 2; divide <= Math.sqrt(num); divide++) {
            if (num % divide == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int startNum : startNumArr) {
            DFS(startNum, 1);
        }

        System.out.println(sb);
    }
}
