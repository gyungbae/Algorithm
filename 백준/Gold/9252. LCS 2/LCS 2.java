import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputA = br.readLine();
        String inputB = br.readLine();
        int lengthA = inputA.length();
        int lengthB = inputB.length();

        int[][] dp = new int[lengthA + 1][lengthB + 1];
        for (int idxA = 1; idxA <= lengthA; idxA++) {
            for (int idxB = 1; idxB <= lengthB; idxB++) {
                if (inputA.charAt(idxA - 1) == inputB.charAt(idxB - 1)) {
                    dp[idxA][idxB] = dp[idxA - 1][idxB - 1] + 1;
                } else {
                    dp[idxA][idxB] = Math.max(dp[idxA - 1][idxB], dp[idxA][idxB - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int searchLengthA = lengthA;
        int searchLengthB = lengthB;
        while (searchLengthA > 0 && searchLengthB > 0) {
            if (inputA.charAt(searchLengthA - 1) == inputB.charAt(searchLengthB - 1)) {
                sb.append(inputA.charAt(searchLengthA - 1));
                searchLengthA--;
                searchLengthB--;
                continue;
            }

            if (dp[searchLengthA - 1][searchLengthB] > dp[searchLengthA][searchLengthB - 1]) {
                searchLengthA--;
                continue;
            }

            searchLengthB--;
        }

        System.out.println(sb.length());
        System.out.println(sb.reverse());
    }
}
