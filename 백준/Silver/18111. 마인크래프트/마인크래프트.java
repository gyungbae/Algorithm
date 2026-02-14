import java.io.*;
import java.util.*;

public class Main {
    static int N, M, B;
    static int[][] heightArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        heightArr = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int height = Integer.parseInt(st.nextToken());
                heightArr[row][col] = height;
            }
        }


        int minTime = Integer.MAX_VALUE;
        int maxTarget = 0;
        for (int target = 256; target >= 0; target--) {
            int time = 0;
            int block = B;

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    int height = heightArr[row][col];
                    if (height > target) {
                        time += (height - target) * 2;
                        block += (height - target);
                    } else {
                        time += (target - height);
                        block -= (target - height);
                    }
                }
            }

            if (block >= 0) {
                if (time < minTime) {
                    minTime = time;
                    maxTarget = target;
                }
            }
        }

        System.out.println(minTime + " " + maxTarget);
    }
}
