import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    static List<int[]> houseList;
    static int houseCnt;
    static List<int[]> chickenList;
    static int chickenCnt;
    static int[][] distance;
    static boolean[] selected;
    static int answer;

    static int getDistance(int[] info1, int[] info2) {
        return Math.abs(info1[0] - info2[0]) + Math.abs(info1[1] - info2[1]);
    }

    static void DFS(int curIdx, int depth) {
        if (depth == M) {
            int sum = 0;
            for (int house = 0; house < houseCnt; house++) {
                int minDist = Integer.MAX_VALUE;
                for (int chicken = 0; chicken < chickenCnt; chicken++) {
                    if (!selected[chicken]) {
                        continue;
                    }

                    minDist = Math.min(minDist, distance[chicken][house]);
                }
                sum += minDist;
                if (sum >= answer) {
                    return;
                }
            }

            answer = sum;
            return;
        }

        for (int chicken = curIdx; chicken < chickenCnt; chicken++) {
            selected[chicken] = true;
            DFS(chicken + 1, depth + 1);
            selected[chicken] = false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        houseList = new ArrayList<>();
        chickenList = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int num = Integer.parseInt(st.nextToken());
                map[row][col] = num;
                if (num == 1) {
                    houseList.add(new int[]{row, col});
                }

                if (num == 2) {
                    chickenList.add(new int[]{row, col});
                }
            }
        }

        chickenCnt = chickenList.size();
        houseCnt = houseList.size();
        distance = new int[chickenCnt][houseCnt];
        for (int chicken = 0; chicken < chickenCnt; chicken++) {
            for (int house = 0; house < houseCnt; house++) {
                distance[chicken][house] = getDistance(chickenList.get(chicken), houseList.get(house));
            }
        }

        selected = new boolean[chickenCnt];
        answer = Integer.MAX_VALUE;
        DFS(0, 0);

        System.out.println(answer);
    }
}
