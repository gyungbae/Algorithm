import java.io.*;
import java.util.*;

/**
 * 1초마다 연산 적용
 * R 연산 : 모든 행 정렬, 행의 개수 >= 열의 개수인 경우
 * C 연산 : 모든 열 정렬, 행의 개수 < 열의 개수인 경우
 * 정렬 - 수 등장 횟수 오름차순, 수 오름차순
 * 배열에 넣을 때는 수, 등장 횟수 반복
 * 정렬 결과 행, 열은 가장 큰 값으로 적용, 빈 자리 0으로 채움(0은 정렬 X)
 * 행, 열 최대 100개까지
 * A[r][c] = k 최소 몇 초?(100초 이내에 불가능하면 -1)
 */
public class Main {
    static int r, c, k;
    static int[][] input;

    static int rowSize = 3;
    static int colSize = 3;

    static void sortRow() {
        int maxColSize = 0;
        List<int[]>[] sortList = new ArrayList[rowSize + 1];
        for (int row = 1; row <= rowSize; row++) {
            sortList[row] = new ArrayList<>();

            int[] count = new int[101];
            for (int col = 1; col <= colSize; col++) {
                int num = input[row][col];
                if(num != 0)
                    count[num]++;
            }

            for (int idx = 1; idx <= 100; idx++) {
                if(count[idx] > 0)
                    sortList[row].add(new int[]{idx, count[idx]});
            }

            Collections.sort(sortList[row], ((o1, o2) -> {
                if(o1[1] == o2[1])
                    return o1[0] - o2[0];

                return o1[1] - o2[1];
            } ));

            maxColSize = Math.max(maxColSize, sortList[row].size() * 2);
        }

        if(maxColSize > 100)
            maxColSize = 100;

        colSize = maxColSize;

        for (int row = 1; row <= rowSize; row++) {
            int colIdx = 1;
            for (int[] info : sortList[row]) {
                if(colIdx > 100)
                    break;

                input[row][colIdx++] = info[0];
                input[row][colIdx++] = info[1];
            }

            for (int idx = colIdx; idx <= colSize; idx++) {
                input[row][idx] = 0;
            }
        }
    }

    static void sortCol() {
        int maxRowSize = 0;
        List<int[]>[] sortList = new ArrayList[colSize + 1];
        for (int col = 1; col <= colSize; col++) {
            sortList[col] = new ArrayList<>();

            int[] count = new int[101];
            for (int row = 1; row <= rowSize; row++) {
                int num = input[row][col];
                if(num != 0)
                    count[num]++;
            }

            for (int idx = 1; idx <= 100; idx++) {
                if(count[idx] > 0)
                    sortList[col].add(new int[]{idx, count[idx]});
            }

            Collections.sort(sortList[col], ((o1, o2) -> {
                if(o1[1] == o2[1])
                    return o1[0] - o2[0];

                return o1[1] - o2[1];
            } ));

            maxRowSize = Math.max(maxRowSize, sortList[col].size() * 2);
        }

        if(maxRowSize > 100)
            maxRowSize = 100;

        rowSize = maxRowSize;

        for (int col = 1; col <= colSize; col++) {
            int rowIdx = 1;
            for (int[] info : sortList[col]) {
                if(rowIdx > 100)
                    break;

                input[rowIdx++][col] = info[0];
                input[rowIdx++][col] = info[1];
            }

            for (int idx = rowIdx; idx <= rowSize; idx++) {
                input[idx][col] = 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        input = new int[101][101];
        for (int row = 1; row <= rowSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= colSize; col++) {
                input[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (answer <= 100) {
            if(input[r][c] == k)
                break;

            answer++;
            if (rowSize >= colSize) {
                sortRow();
            } else {
                sortCol();
            }
        }

        System.out.println(answer > 100 ? -1 : answer);
    }
}
