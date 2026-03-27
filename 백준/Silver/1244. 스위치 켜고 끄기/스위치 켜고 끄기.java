import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 1; idx <= n; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        int studentCount = Integer.parseInt(br.readLine());

        for (int input = 0; input < studentCount; input++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                for (int idx = num; idx <= n; idx += num) {
                    arr[idx] = 1 - arr[idx];
                }
            }

            else {
                int left = num;
                int right = num;

                while (left - 1 >= 1 && right + 1 <= n && arr[left - 1] == arr[right + 1]) {
                    left--;
                    right++;
                }

                for (int idx = left; idx <= right; idx++) {
                    arr[idx] = 1 - arr[idx];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int idx = 1; idx <= n; idx++) {
            sb.append(arr[idx]).append(" ");
            if (idx % 20 == 0)
                sb.append("\n");
        }

        System.out.println(sb);
    }
}
