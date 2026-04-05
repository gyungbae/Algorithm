import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = X - 1;
        int sum = 0;
        for(int i = left; i <= right; i++){
            sum += arr[i];
        }

        int max = 0;
        int count = 0;
        max = sum;
        count++;

        while (true) {
            sum -= arr[left];
            left++;

            right++;

            if (right == N)
                break;

            sum += arr[right];

            if (sum > max) {
                max = sum;
                count = 1;
            } else if (sum == max) {
                count++;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
            return;
        }
        
        System.out.println(max);
        System.out.println(count);
    }
}
