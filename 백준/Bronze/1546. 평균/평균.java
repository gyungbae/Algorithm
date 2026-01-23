import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int idx = 0; idx < N; idx++) {
            int num = Integer.parseInt(st.nextToken());
            numArr[idx] = num;
            max = Math.max(num, max);
        }

        double sum = 0;
        for (int num : numArr) {
            sum += (num / (double) max * 100);
        }

        System.out.println(sum / N);
    }
}
