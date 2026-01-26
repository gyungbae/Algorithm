import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        Arrays.fill(answer, -1);
        Stack<Integer> stack = new Stack<>();
        for (int idx = 0; idx < N; idx++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[idx]) {
                answer[stack.pop()] = arr[idx];
            }

            stack.push(idx);
        }

        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
