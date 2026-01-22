import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] numArr;
    static int M;
    static int[] findNumArr;

    static boolean binarySearch(int findNum) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (numArr[mid] > findNum) {
                right = mid - 1;
            } else if (numArr[mid] < findNum) {
                left = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            numArr[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numArr);

        M = Integer.parseInt(br.readLine());
        findNumArr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < M; idx++) { 
            findNumArr[idx] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int findNum : findNumArr) {
            sb.append(binarySearch(findNum) ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }
}
