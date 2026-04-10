import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        int require = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
            require += arr[i];
        }

        int M = Integer.parseInt(br.readLine());
        if (require <= M) {
            System.out.println(max);
            return;
        }
        
        int left = 1;
        int right = max;
        int answer = 0;
        while(left <= right) {
            int mid = left + (right - left) / 2;

            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += Math.min(arr[i], mid);
            }

            if (sum <= M) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
