import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] arr = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int idx = 0; idx < M; idx++){
            arr[idx] = Integer.parseInt(st.nextToken());
        }


        int left = 0;
        int right = N;
        int answer = N;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int prevEnd = 0;
            boolean flashed = true;
            for (int idx = 0; idx < M; idx++) {
                int from = arr[idx] - mid;
                int to = arr[idx] + mid;

                if (from > prevEnd) {
                    flashed = false;
                    break;
                }
                
                prevEnd = to;
            }

            if(prevEnd < N)
                flashed = false;

            if (flashed) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
