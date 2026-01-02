import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H;
    static int[][][] map;

    static int answer;
    static int[] deltaRow = {-1, 1, 0, 0, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1, 0, 0};
    static int[] deltaHeight = {0, 0, 0, 0, -1, 1};

    static boolean canProceed(int row, int col, int height) {
        if (row < 0 || row >= N || col < 0 || col >= M || height < 0 || height >= H) {
            return false;
        }

        if (map[row][col][height] != 0) {
            return false;
        }

        return true;
    }

    static void bfs(List<int[]> ripeList) {
        Queue<Node> q = new LinkedList<>();
        int day = 0;

        for (int[] arr : ripeList) {
            q.add(new Node(arr[0], arr[1], arr[2]));
        }

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node current = q.poll();

                for (int delta = 0; delta < 6; delta++) {
                    int nextRow = current.row + deltaRow[delta];
                    int nextCol = current.col + deltaCol[delta];
                    int nextHeight = current.height + deltaHeight[delta];

                    if (canProceed(nextRow, nextCol, nextHeight)) {
                        map[nextRow][nextCol][nextHeight] = 1;
                        q.offer(new Node(nextRow, nextCol, nextHeight));
                    }
                }
            }
            day++;
        }

        answer = day - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N][M][H];
        List<int[]> ripeList = new ArrayList<>();
        for (int height = 0; height < H; height++) {
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < M; col++) {
                    int val = Integer.parseInt(st.nextToken());

                    if (val == 1) {
                        ripeList.add(new int[]{row, col, height});
                    }

                    map[row][col][height] = val;
                }
            }
        }

        bfs(ripeList);
        for (int height = 0; height < H; height++) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (map[row][col][height] == 0) {
                        answer = -1;
                        break;
                    }
                }
            }
        }
        
        System.out.println(answer);
    }
}

class Node {
    int row, col, height;

    Node(int row, int col, int height) {
        this.row = row;
        this.col = col;
        this.height = height;
    }
}
