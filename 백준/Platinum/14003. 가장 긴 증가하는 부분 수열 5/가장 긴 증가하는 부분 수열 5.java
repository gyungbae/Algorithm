import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] inputArr;

    static int[] tailNumArr;
    static int[] tailIdxArr;
    static int[] prevIdxArr;

    static int searchIdx(int sequenceSize, int findNum) {
        int left = 0;
        int right = sequenceSize;

        while (left < right) {
            int mid = (left + right) / 2;

            if (tailNumArr[mid] >= findNum) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        inputArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            inputArr[idx] = Integer.parseInt(st.nextToken());
        }

        tailNumArr = new int[N];
        tailIdxArr = new int[N];
        prevIdxArr = new int[N];
        int sequenceSize = 0;
        for (int idx = 0; idx < N; idx++) {
            int sequenceIdx = searchIdx(sequenceSize, inputArr[idx]);

            tailNumArr[sequenceIdx] = inputArr[idx];
            tailIdxArr[sequenceIdx] = idx;

            prevIdxArr[idx] = sequenceIdx == 0 ? -1 : tailIdxArr[sequenceIdx - 1];

            if (sequenceIdx == sequenceSize) {
                sequenceSize++;
            }
        }

        int[] answer = new int[sequenceSize];
        int currentIdx = tailIdxArr[sequenceSize - 1];
        for (int idx = sequenceSize - 1; idx >= 0; idx--) {
            answer[idx] = inputArr[currentIdx];
            currentIdx = prevIdxArr[currentIdx];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(sequenceSize).append("\n");
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
