import java.io.*;
import java.util.*;

public class Main {
    static int A, B, C;

    static long search(int num, int power) {
        if (power == 0) {
            return 1;
        }

        long half = search(num, power / 2);
        long result = half * half % C;

        if (power % 2 == 1) {
            result = (result * num) % C;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(search(A % C, B));
    }
}
