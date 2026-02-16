import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (i == 0) {
                maxDp[0] = minDp[0] = a;
                maxDp[1] = minDp[1] = b;
                maxDp[2] = minDp[2] = c;
                continue;
            }

            int pMax0 = maxDp[0], pMax1 = maxDp[1], pMax2 = maxDp[2];
            int pMin0 = minDp[0], pMin1 = minDp[1], pMin2 = minDp[2];

            maxDp[0] = a + Math.max(pMax0, pMax1);
            maxDp[1] = b + Math.max(pMax0, Math.max(pMax1, pMax2));
            maxDp[2] = c + Math.max(pMax1, pMax2);

            minDp[0] = a + Math.min(pMin0, pMin1);
            minDp[1] = b + Math.min(pMin0, Math.min(pMin1, pMin2));
            minDp[2] = c + Math.min(pMin1, pMin2);
        }

        int maxAns = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
        int minAns = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));

        System.out.println(maxAns + " " + minAns);
    }
}
