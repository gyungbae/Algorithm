import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] input;
    static int B, C;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            input[idx] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        long answer = 0;
        for (int num : input) {
            if (num <= B) {
                answer++;
                continue;
            }

            num -= B;
            answer++;

            int count = num / C;
            num -= C * count;

            if (num > 0) {
                answer += count + 1;
            } else {
                answer += count;
            }
        }

        System.out.println(answer);
    }
}
