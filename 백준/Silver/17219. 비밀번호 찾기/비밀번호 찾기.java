import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, String> map = new HashMap<>();
        for(int input = 1; input <= N; input++) {
            st = new StringTokenizer(br.readLine());
            String key = st.nextToken();
            String val = st.nextToken();

            map.put(key, val);
        }

        StringBuilder sb = new StringBuilder();
        for(int input = 1; input <= M; input++) {
            sb.append(map.get(br.readLine())).append("\n");
        }

        System.out.println(sb);
    }
}
