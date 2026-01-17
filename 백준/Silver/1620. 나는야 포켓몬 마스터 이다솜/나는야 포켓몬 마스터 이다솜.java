import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, String> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for(int input = 1; input <= N; input++) {
            String name = br.readLine();
            map1.put(input, name);
            map2.put(name, input);
        }

        StringBuilder sb = new StringBuilder();
        for(int input = 1; input <= M; input++) {
            String str = br.readLine();
            if('0' <= str.charAt(0) && str.charAt(0) <= '9') {
                sb.append(map1.get(Integer.parseInt(str))).append("\n");
            } else {
                sb.append(map2.get(str)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
