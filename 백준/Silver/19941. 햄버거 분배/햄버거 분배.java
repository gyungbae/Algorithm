import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] arr = br.readLine().toCharArray();
        boolean[] eaten = new boolean[N];

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (arr[i] != 'P') continue;

            int left = Math.max(0, i - K);
            int right = Math.min(N - 1, i + K);

            for (int j = left; j <= right; j++) {
                if (arr[j] == 'H' && !eaten[j]) {
                    eaten[j] = true;
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
