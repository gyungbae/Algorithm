import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int answer = 0;
        while (N > 0) {
            int size = (int) Math.pow(2, N);
            int half = size / 2;
            if (r >= half) {
                if (c >= half) {
                    answer += 3 * half * half;
                    r -= half;
                    c -= half;
                } else {
                    answer += 2 * half * half;
                    r -= half;
                }
            } else {
                if (c >= half) {
                    answer += half * half;
                    c -= half;
                }
            }

            N--;
        }

        System.out.println(answer);
    }
}
