import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map;

    static int size;
    static List<Integer> list = new ArrayList<>();
    static boolean[][] visited;
    
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static void BFS(int row, int col) {
        Queue<int[]> q = new ArrayDeque<>();

        visited[row][col] = true;
        q.offer(new int[]{row, col});

        while(!q.isEmpty()) {
            int[] current = q.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            size++;

            for(int delta = 0; delta < 4; delta++) {
                int nextRow = currentRow + deltaRow[delta];
                int nextCol = currentCol + deltaCol[delta];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                    continue;
                }

                if(visited[nextRow][nextCol] || map[nextRow][nextCol] == 0) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                q.offer(new int[]{nextRow, nextCol});
            }         
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int row = 0; row < N; row++) {
            String line = br.readLine();
            for(int col = 0; col < N; col++) {
                map[row][col] = line.charAt(col) - '0';
            }
        }

        visited = new boolean[N][N];
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(!visited[row][col] && map[row][col] == 1) {
                    size = 0;
                    BFS(row, col);
                    list.add(size);
                }
            }
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for(int num : list) {
            sb.append(num).append("\n");
        }

        System.out.println(sb);
    }
}
