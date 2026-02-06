import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine();

        int answer = 0;
        int IOICount = 0;

        for (int idx = 1; idx < M - 1; idx++) {
            if (S.charAt(idx - 1) == 'I' && S.charAt(idx) == 'O' && S.charAt(idx + 1) == 'I') {
                IOICount++;
                if (IOICount >= N) {
                    answer++;
                }
                idx++;
            } else {
                IOICount = 0;
            }
        }

        System.out.println(answer);
    }
}
