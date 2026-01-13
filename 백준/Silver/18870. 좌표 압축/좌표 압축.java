import java.io.*;
import java.util.*;

public class Main {
    static int N;

    static Map<Integer, List<Integer>> map;
    static int[] resultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            int val = Integer.parseInt(st.nextToken());
            if (!map.containsKey(val)) {
                map.put(val, new ArrayList<>());
            }

            map.get(val).add(idx);
        }

        int rank = 0;
        resultArr = new int[N];
        for (int key : map.keySet()) {
            List<Integer> idxList = map.get(key);
            for (int idx : idxList) {
                resultArr[idx] = rank;
            }

            rank++;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : resultArr) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }
}
