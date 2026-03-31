import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int[] count = new int[201];
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int idx = 0; idx < N; idx++) {
                int num = Integer.parseInt(st.nextToken());
                count[num]++;
                arr[idx] = num;
            }

            int score = 1;
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int idx = 0; idx < N; idx++) {
                int team = arr[idx];

                if(count[team] < 6)
                    continue;

                map.putIfAbsent(team, new ArrayList<>());
                map.get(team).add(score);
                score++;
            }

            int minSum = Integer.MAX_VALUE;
            int prevFifth = 0;
            int win = 0;
            Set<Integer> keySet = map.keySet();
            for (int team : keySet) {
                List<Integer> scores = map.get(team);
                int sum = 0;
                for(int idx = 0; idx < 4; idx++) {
                    int value = scores.get(idx);
                    sum += value;
                }

                int fifth = scores.get(4);

                if (minSum > sum) {
                    minSum = sum;
                    win = team;
                    prevFifth = fifth;
                }

                if(minSum == sum && fifth < prevFifth) {
                    win = team;
                    prevFifth = fifth;
                }
            }

            sb.append(win).append("\n");
        }

        System.out.println(sb);
    }
}
