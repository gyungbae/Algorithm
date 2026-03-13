import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[3];

            for (int i = 0; i < 3; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0) {
                break;
            }

            Arrays.sort(arr);

            if (arr[0] * arr[0] + arr[1] * arr[1] == arr[2] * arr[2]) {
                sb.append("right\n");
            } else {
                sb.append("wrong\n");
            }
        }

        System.out.print(sb);
    }
}