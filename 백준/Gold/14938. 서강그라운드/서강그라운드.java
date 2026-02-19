import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r;
    static int[] input;
    static int[][] distance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        input = new int[n + 1];
        for (int node = 1; node <= n; node++) {
            input[node] = Integer.parseInt(st.nextToken());
        }

        distance = new int[n + 1][n + 1];
        for (int node = 1; node <= n; node++) {
            Arrays.fill(distance[node], Integer.MAX_VALUE);
            distance[node][node] = 0;
        }

        for (int edge = 1; edge <= r; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            distance[from][to] = Math.min(distance[from][to], weight);
            distance[to][from] = Math.min(distance[to][from], weight);
        }

        for (int viaNode = 1; viaNode <= n; viaNode++) {
            for (int fromNode = 1; fromNode <= n; fromNode++) {
                if(distance[fromNode][viaNode] == Integer.MAX_VALUE)
                    continue;

                for (int toNode = 1; toNode <= n; toNode++) {
                    if(distance[viaNode][toNode] == Integer.MAX_VALUE)
                        continue;

                    distance[fromNode][toNode] = Math.min(distance[fromNode][toNode],
                            distance[fromNode][viaNode] + distance[viaNode][toNode]);
                }
            }
        }

        int answer = 0;
        for (int fromNode = 1; fromNode <= n; fromNode++) {
            int sum = 0;
            for (int toNode = 1; toNode <= n; toNode++) {
                if (distance[fromNode][toNode] <= m) {
                    sum += input[toNode];
                }
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}
