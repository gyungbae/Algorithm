import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] likeInfo;

    static Queue<Integer> queue = new ArrayDeque<>();
    static int[][] map;
    static int[][] blankInfo;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static boolean isInRange(int row, int col) {
        if(row < 0 || row >= N || col < 0 || col >= N)
            return false;

        return true;
    }

    static void setStudent(int student, int row, int col) {
        map[row][col] = student;

        for (int delta = 0; delta < 4; delta++) {
            int nextRow = row + deltaRow[delta];
            int nextCol = col + deltaCol[delta];

            if (isInRange(nextRow, nextCol))
                blankInfo[nextRow][nextCol]--;
        }
    }

    static void fillBlankInfo() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int count = 0;

                for (int delta = 0; delta < 4; delta++) {
                    int nextRow = row + deltaRow[delta];
                    int nextCol = col + deltaCol[delta];

                    if(isInRange(nextRow, nextCol))
                        count++;
                }

                blankInfo[row][col] = count;
            }
        }
    }

    static List<int[]> searchFirst(int student) {
        List<int[]> result = new ArrayList<>();
        int[][] likeCountInfo = new int[N][N];

        int max = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if(map[row][col] != 0)
                    continue;

                int count = 0;

                for (int delta = 0; delta < 4; delta++) {
                    int nextRow = row + deltaRow[delta];
                    int nextCol = col + deltaCol[delta];

                    if (isInRange(nextRow, nextCol)) {
                        for (int like : likeInfo[student]) {
                            if(map[nextRow][nextCol] == like)
                                count++;
                        }
                    }
                }

                likeCountInfo[row][col] = count;
                max = Math.max(max, count);
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if(map[row][col] != 0)
                    continue;

                if(likeCountInfo[row][col] == max)
                    result.add(new int[]{row, col});
            }
        }

        return result;
    }

    static List<int[]> searchSecond(int student, List<int[]> firstResult) {
        List<int[]> result = new ArrayList<>();
        int[] blankCountInfo = new int[firstResult.size()];

        int max = 0;
        for (int idx = 0; idx < firstResult.size(); idx++) {
            int[] info = firstResult.get(idx);
            int row = info[0];
            int col = info[1];

            blankCountInfo[idx] = blankInfo[row][col];
            max = Math.max(max, blankCountInfo[idx]);
        }

        for (int idx = 0; idx < firstResult.size(); idx++) {
            if (blankCountInfo[idx] == max)
                result.add(firstResult.get(idx));
        }

        return result;
    }

    static int[] searchLast(int student, List<int[]> secondResult) {
        int bestRow = N;
        int bestCol = N;
        for (int[] info : secondResult) {
            int row = info[0];
            int col = info[1];

            if (row < bestRow) {
                bestRow = row;
                bestCol = col;
            }

            if (row == bestRow) {
                bestCol = Math.min(bestCol, col);
            }
        }

        return new int[]{bestRow, bestCol};
    }

    static int getScore() {
        int score = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int student = map[row][col];

                int count = 0;
                for (int delta = 0; delta < 4; delta++) {
                    int nextRow = row + deltaRow[delta];
                    int nextCol = col + deltaCol[delta];

                    if (isInRange(nextRow, nextCol)) {
                        int num = map[nextRow][nextCol];
                        for (int likeStudent : likeInfo[student]) {
                            if(num == likeStudent)
                                count++;
                        }
                    }
                }

                switch (count) {
                    case 1 -> score += 1;
                    case 2 -> score += 10;
                    case 3 -> score += 100;
                    case 4 -> score += 1000;
                }
            }
        }

        return score;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        likeInfo = new int[N * N + 1][4];
        for (int input = 1; input <= N * N; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            queue.offer(student);

            for (int idx = 0; idx < 4; idx++) {
                likeInfo[student][idx] = Integer.parseInt(st.nextToken());
            }
        }

        blankInfo = new int[N][N];
        fillBlankInfo();

        map = new int[N][N];
        while (!queue.isEmpty()) {
            int student = queue.poll();

            List<int[]> firstResult = searchFirst(student);
            if(firstResult.size() == 1) {
                int[] info = firstResult.get(0);
                setStudent(student, info[0], info[1]);
                continue;
            }

            List<int[]> secondResult = searchSecond(student, firstResult);
            if(secondResult.size() == 1) {
                int[] info = secondResult.get(0);
                setStudent(student, info[0], info[1]);
                continue;
            }

            int[] lastResult = searchLast(student, secondResult);
            setStudent(student, lastResult[0], lastResult[1]);
        }

        System.out.println(getScore());
    }
}
