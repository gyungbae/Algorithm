import java.io.*;
import java.util.*;

/**
 * 상하좌우 인접 두 나라 인구 차이가 L~R이면 국경선 하루 동안 오픈
 * 국경선이 모두 열리면, 인구 이동
 * 각 칸의 인구수는 연합 인구수 / 칸 개수(소수점 버림)
 * 연합 해체, 국경선 닫음
 * 인구 이동이 며칠 동안 발생하는지를 구하시오
 */
public class Main {
    static int N, L, R;
    static int[][] input;

    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    static boolean[][] visited;
    static int answer;

    static List<List<int[]>> createUnion() {
        List<List<int[]>> result = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (!visited[row][col]) {
                    List<int[]> union = BFS(row, col);

                    if(union.size() == 1)
                        continue;

                    result.add(union);
                }
            }
        }

        return result;
    }

    static void move(List<int[]> union) {
        int size = union.size();
        int sum = 0;
        for (int[] info : union) {
            sum += input[info[0]][info[1]];
        }

        int value = sum / size;
        for (int[] info : union) {
            input[info[0]][info[1]] = value;
        }
    }

    static List<int[]> BFS(int row, int col) {
        List<int[]> result = new ArrayList<>();

        Queue<int[]> queue = new ArrayDeque<>();

        visited[row][col] = true;
        queue.offer(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            result.add(current);

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = current[0] + deltaRow[delta];
                int nextCol = current[1] + deltaCol[delta];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
                    continue;

                int gap = Math.abs(input[current[0]][current[1]] - input[nextRow][nextCol]);
                if(visited[nextRow][nextCol] || !(L <= gap && gap <= R))
                    continue;

                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        input = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                input[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][N];

            List<List<int[]>> unionList = createUnion();

            if(unionList.size() == 0)
                break;

            answer++;
            for (List<int[]> union : unionList) {
                move(union);
            }
        }

        System.out.println(answer);
    }
}
