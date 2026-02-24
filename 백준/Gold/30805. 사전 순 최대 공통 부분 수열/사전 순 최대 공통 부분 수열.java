import java.io.*;
import java.util.*;

public class Main {
    static int search(int[] arr, int fromIdx, int target) {
        for (int idx = fromIdx; idx < arr.length; idx++) {
            if (arr[idx] == target) {
                return idx;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] input1 = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            input1[idx] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] input2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < M; idx++) {
            input2[idx] = Integer.parseInt(st.nextToken());
        }

        List<Integer> answer = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        while (true) {
            int max = 0;
            int valueIdx1 = -1;
            int valueIdx2 = -1;
            for (int value = 100; value >= 1; value--) {
                valueIdx1 = search(input1, idx1, value);
                if (valueIdx1 == -1) 
                    continue;

                valueIdx2 = search(input2, idx2, value);
                if (valueIdx2 == -1) 
                    continue;
                
                max = value;
                break;
            }

            if (max == 0) 
                break;

            answer.add(max);
            idx1 = valueIdx1 + 1;
            idx2 = valueIdx2 + 1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        if (!answer.isEmpty()) {
            for (int value : answer) {
                sb.append(value).append(" ");
            }
        }

        System.out.println(sb);
    }
}
