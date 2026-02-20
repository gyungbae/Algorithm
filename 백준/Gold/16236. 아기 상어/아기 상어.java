import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] input;

    static Shark shark;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static int answer;

    static Fish searchFish() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        visited[shark.row][shark.col] = true;
        queue.offer(new int[]{shark.row, shark.col, 0});

        int fishRow = N;
        int fishCol = N;
        int minDistance = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];

            if (distance > minDistance)
                break;

            int fishSize = input[row][col];
            if(1 <= fishSize && fishSize <= 6 && fishSize < shark.size) {
                if (distance == minDistance) {
                    if (fishRow == row) {
                        fishCol = Math.min(fishCol, col);
                    }

                    if (row < fishRow) {
                        fishRow = row;
                        fishCol = col;
                    }
                } else {
                    fishRow = row;
                    fishCol = col;
                    minDistance = distance;
                }
            }

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = row + deltaRow[delta];
                int nextCol = col + deltaCol[delta];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                    continue;

                if(visited[nextRow][nextCol] || input[nextRow][nextCol] > shark.size)
                    continue;

                visited[nextRow][nextCol] = true;
                if (1 <= input[nextRow][nextCol] && input[nextRow][nextCol] <= 6) {
                    queue.offer(new int[]{nextRow, nextCol, current[2] + 1});
                } else {
                    queue.offer(new int[]{nextRow, nextCol, current[2] + 1});
                }
            }
        }

        if (fishRow == N || fishCol == N) {
            return null;
        }

        return new Fish(fishRow, fishCol, input[fishRow][fishCol], minDistance);
    }

    static void eat(Fish fish) {
        shark.row = fish.row;
        shark.col = fish.col;
        shark.eatCount++;
        if (shark.eatCount == shark.size) {
            shark.size++;
            shark.eatCount = 0;
        }

        input[fish.row][fish.col] = 0;
        answer += fish.distance;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int num = Integer.parseInt(st.nextToken());
                input[row][col] = num;
                if (num == 9) {
                    shark = new Shark(row, col);
                    input[row][col] = 0;
                }
            }
        }

        while (true) {
            Fish fish = searchFish();
            if (fish == null)
                break;

            eat(fish);
        }

        System.out.println(answer);
    }
}

class Shark {
    int row, col;
    int size;
    int eatCount;

    public Shark(int row, int col) {
        this.row = row;
        this.col = col;
        this.size = 2;
    }
}

class Fish {
    int row, col;
    int size;
    int distance;

    public Fish(int row, int col, int size, int distance) {
        this.row = row;
        this.col = col;
        this.size = size;
        this.distance = distance;
    }
}
