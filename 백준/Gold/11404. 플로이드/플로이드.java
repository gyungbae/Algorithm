import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;

    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (row == col) {
                    dist[row][col] = 0;
                    continue;
                }

                dist[row][col] = Integer.MAX_VALUE;
            }
        }

        for (int edge = 1; edge <= M; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            dist[from][to] = Math.min(dist[from][to], weight);
        }

        for (int viaNode = 1; viaNode <= N; viaNode++) {
            for (int from = 1; from <= N; from++) {
                if (dist[from][viaNode] == Integer.MAX_VALUE) {
                    continue;
                }

                for (int to = 1; to <= N; to++) {
                    if (dist[viaNode][to] == Integer.MAX_VALUE) {
                        continue;
                    }

                    int nextDist = dist[from][viaNode] + dist[viaNode][to];
                    if (nextDist < dist[from][to]) {
                        dist[from][to] = nextDist;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (dist[row][col] == Integer.MAX_VALUE) {
                    sb.append(0).append(" ");
                    continue;
                }
                
                sb.append(dist[row][col]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
