import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        long require = 0;
        int max = 0;
        for (int idx = 0; idx < N; idx++) {
            int value = Integer.parseInt(st.nextToken());
            arr[idx] = value;
            require += value;
            max = Math.max(max, value);
        }

        int M = Integer.parseInt(br.readLine());
        if (require <= M) {
            System.out.println(max);
            return;
        }

        int left = 0;
        int right = max;
        max = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            long sum = 0;
            for (int budget : arr) {
                sum += Math.min(budget, mid);
            }

            if (sum <= M) {
                max = mid;      
                left = mid + 1;    
            } else {
                right = mid - 1;   
            }
        }

        System.out.println(max);
    }
}
