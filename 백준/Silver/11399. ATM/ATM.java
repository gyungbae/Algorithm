import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] timeArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        timeArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            timeArr[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(timeArr);

        int answer = 0;
        for (int idx = 0; idx < N; idx++) {
            answer += timeArr[idx] * (N - idx);
        }

        System.out.println(answer);
    }
}
