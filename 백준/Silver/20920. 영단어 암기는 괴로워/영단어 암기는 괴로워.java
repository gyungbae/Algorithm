import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();

        for (int input = 0; input < N; input++) {
            String word = br.readLine();

            if (word.length() < M) 
                continue;

            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> words = new ArrayList<>(map.keySet());

        words.sort((o1, o2) -> {
            if (!map.get(o1).equals(map.get(o2))) {
                return map.get(o2) - map.get(o1);
            }

            if (o1.length() != o2.length()) {
                return o2.length() - o1.length();
            }

            return o1.compareTo(o2);
        });

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word).append('\n');
        }

        System.out.print(sb);
    }
}
