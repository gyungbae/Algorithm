import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] distance = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                distance[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int viaNode = 0; viaNode < N; viaNode++) {
            for (int fromNode = 0; fromNode < N; fromNode++) {
                if (distance[fromNode][viaNode] == 0) {
                    continue;
                }

                for (int toNode = 0; toNode < N; toNode++) {
                    if (distance[viaNode][toNode] == 1) {
                        distance[fromNode][toNode] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                sb.append(distance[row][col]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
