import java.io.*;
import java.util.*;

public class Main {
    static int M;

    static boolean[] flagArr = new boolean[21];
    static StringBuilder sb = new StringBuilder();

    static void function(String order, int num) {
        switch (order) {
            case "add":
                flagArr[num] = true;
                break;
            case "remove":
                flagArr[num] = false;
                break;
            case "check":
                sb.append(flagArr[num] ? 1 : 0).append("\n");
                break;
            case "toggle":
                flagArr[num] = !flagArr[num];
                break;
            case "all":
                Arrays.fill(flagArr, true);
                break;
            default:
                Arrays.fill(flagArr, false);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        for (int input = 1; input <= M; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            int num = 0;
            if (st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
            }

            function(order, num);
        }

        System.out.println(sb);
    }
}
