import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int input = 0; input < k; input++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if (command == 'I') {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    if (map.isEmpty()) {
                        continue;
                    }

                    int key = (num == 1) ? map.lastKey() : map.firstKey();

                    int count = map.get(key);
                    if (count == 1) {
                        map.remove(key);
                    } else {
                        map.put(key, count - 1);
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey())
                        .append(" ")
                        .append(map.firstKey())
                        .append("\n");
            }
        }

        System.out.print(sb);
    }
}
