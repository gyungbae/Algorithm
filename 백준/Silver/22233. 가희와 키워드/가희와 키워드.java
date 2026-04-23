import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> keywords = new HashSet<>();

        for (int input = 0; input < N; input++) {
            keywords.add(br.readLine());
        }

        StringBuilder sb = new StringBuilder();

        for (int idx = 0; idx < M; idx++) {
            String[] words = br.readLine().split(",");

            for (String word : words) {
                keywords.remove(word);
            }

            sb.append(keywords.size()).append('\n');
        }

        System.out.print(sb);
    }
}
