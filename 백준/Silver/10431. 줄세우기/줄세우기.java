import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int P = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (P-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());

            int[] input = new int[20];
            for (int idx = 0; idx < 20; idx++) {
                input[idx] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;
            for (int stdIdx = 0; stdIdx < 20; stdIdx++) {
                int num = input[stdIdx];

                int insertIdx = -1;
                for (int compareIdx = 0; compareIdx < stdIdx; compareIdx++) {
                    if (num < input[compareIdx]) {
                        insertIdx = compareIdx;
                        break;
                    }
                }

                if (insertIdx != -1) {
                    for (int idx = stdIdx; idx > insertIdx; idx--) {
                        input[idx] = input[idx - 1];
                        answer++;
                    }

                    input[insertIdx] = num;
                }
            }

            sb.append(T + " " + answer).append("\n");
        }

        System.out.println(sb);
    }
}
