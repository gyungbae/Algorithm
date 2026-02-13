import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int answer = 0;

        while (B > A) {
            if (B % 10 == 1) {
                B /= 10;
                answer++;
            } else if (B % 2 == 0) {
                B /= 2;
                answer++;
            } else {
                break;
            }
        }

        System.out.println(B == A ? answer + 1 : -1);
    }
}
