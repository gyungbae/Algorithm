import java.io.*;
import java.util.*;

public class Main {
    static int T, n;
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            for (int input = 1; input <= n; input++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();

                if (map.containsKey(type)) {
                    map.put(type, map.get(type) + 1);
                } else {
                    map.put(type, 1);
                }
            }

            int answer = 1;
            for (int value : map.values()) {
                answer *= (value + 1);
            }
            sb.append(answer - 1).append("\n");
        }

        System.out.println(sb);
    }
}
