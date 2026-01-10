import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for (int input = 1; input <= N; input++) {
            set.add(br.readLine());
        }

        int count = 0;
        List<String> list = new ArrayList<>();
        for (int input = 1; input <= M; input++) {
            String str = br.readLine();
            if (set.contains(str)) {
                count++;
                list.add(str);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        Collections.sort(list);
        for (String str : list) {
            sb.append(str).append("\n");
        }

        System.out.println(sb);
    }
}
