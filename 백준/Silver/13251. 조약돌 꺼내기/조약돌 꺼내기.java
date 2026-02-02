import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine()); // 색상 수
        int[] countArr = new int[M];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalCount = 0;
        for (int color = 0; color < M; color++) {
            countArr[color] = Integer.parseInt(st.nextToken());
            totalCount += countArr[color];
        }

        int K = Integer.parseInt(br.readLine()); 

        if (K == 1) {
            System.out.println(1.0);
            return;
        }

        double answer = 0.0;

        for (int color = 0; color < M; color++) {
            if (countArr[color] < K) continue;

            double result = 1.0;
            for (int pick = 0; pick < K; pick++) {
                result *= (double)(countArr[color] - pick) / (double)(totalCount - pick);
            }
            answer += result;
        }

        System.out.println(answer);
    }
}
