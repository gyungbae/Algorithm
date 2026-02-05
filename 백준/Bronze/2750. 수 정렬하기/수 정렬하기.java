import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(br.readLine());
        }

        for (int sortedCount = 0; sortedCount < N - 1; sortedCount++) {
            for (int idx = 0; idx < N - sortedCount - 1; idx++) {
                if (arr[idx] > arr[idx + 1]) {
                    int tmp = arr[idx];
                    arr[idx] = arr[idx + 1];
                    arr[idx + 1] = tmp;
                }
            }
        }

        for (int num : arr) {
            System.out.println(num);
        }
    }
}
