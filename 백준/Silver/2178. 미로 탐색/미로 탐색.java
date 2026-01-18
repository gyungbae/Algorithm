import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static int[][] map;
    
    static int[][] record;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static void BFS() {
        Queue<int[]> q = new ArrayDeque<>();

        record[0][0] = 1;
        q.offer(new int[]{0, 0});

        while(!q.isEmpty()) {
            int[] current = q.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            if(currentRow == N - 1 && currentCol == M - 1) {
                break;
            }

            for(int delta = 0; delta < 4; delta++) {
                int nextRow = currentRow + deltaRow[delta];
                int nextCol = currentCol + deltaCol[delta];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                    continue;
                }

                if(map[nextRow][nextCol] == 0 || record[nextRow][nextCol] != 0) {
                    continue;
                }

                record[nextRow][nextCol] = record[currentRow][currentCol] + 1;
                q.offer(new int[]{nextRow, nextCol});
            }         
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int row = 0; row < N; row++) {
            String line = br.readLine();
            for(int col = 0; col < M; col++) {
                map[row][col] = line.charAt(col) - '0';
            }
        }

        record = new int[N][M];
        
        BFS();

        System.out.println(record[N-1][M-1]);
    }
}
