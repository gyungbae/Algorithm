import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] inputArr;

    static int[] countArr;
    static int typeCount;
    static int maxLength;
    static int left;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        inputArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            inputArr[idx] = Integer.parseInt(st.nextToken());
        }

        countArr = new int[10];
        typeCount = 0;
        left = 0;
        maxLength = 0;

        for (int right = 0; right < N; right++) {
            int type = inputArr[right];
            if (countArr[type]++ == 0) {
                typeCount++;
            }

            while (typeCount > 2) {
                type = inputArr[left];
                if (--countArr[type] == 0) {
                    typeCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }
}
