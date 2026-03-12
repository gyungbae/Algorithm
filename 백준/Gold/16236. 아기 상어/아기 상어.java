import java.io.*;
import java.util.*;

/**
 * 아기 상어 사이즈 2 시작, 상하좌우 이동
 * 큰 물고기 이동 X, 같은 사이즈 이동 O, 작은 사이즈 이동 O/식사 O
 * 가장 가까운 -> 가장 위 -> 가장 왼쪽 물고기부터 식사
 * 이동에 1초
 * 크기와 같은 수의 물고기를 먹으면 크기 증가
 *
 * 최단거리 >> BFS
 * Shark, Fish class 생성
 * searchFish(), eat() 반복
 */
public class Main {
    static int N;
    static int[][] input;

    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static Shark shark;
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
            int currentRow = current[0];
            int currentCol = current[1];
            int distance = current[2];

            if(distance > minDistance)
                break;

            //먹을 수 있는 물고기
            int currentValue = input[currentRow][currentCol];
            if (1 <= currentValue && currentValue <= 6 && currentValue < shark.size) {
                if (distance == minDistance) {  //가장 위, 가장 왼쪽 판별
                    if (fishRow == currentRow)
                        fishCol = Math.min(fishCol, currentCol);

                    if (currentRow < fishRow) {
                        fishRow = currentRow;
                        fishCol = currentCol;
                    }
                } else {    //최단거리, 물고기 갱신
                    fishRow = currentRow;
                    fishCol = currentCol;
                    minDistance = distance;
                }
            }

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = currentRow + deltaRow[delta];
                int nextCol = currentCol + deltaCol[delta];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                    continue;

                if(visited[nextRow][nextCol] || input[nextRow][nextCol] > shark.size)
                    continue;

                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol, distance + 1});
            }
        }

        if(fishRow == N)
            return null;

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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                input[row][col] = Integer.parseInt(st.nextToken());
                if (input[row][col] == 9) {
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
