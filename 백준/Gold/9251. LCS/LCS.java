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
            char charA = inputA.charAt(idxA - 1);
            for (int idxB = 1; idxB <= lengthB; idxB++) {
                char charB = inputB.charAt(idxB - 1);

                if (charA == charB) {
                    dp[idxA][idxB] = dp[idxA - 1][idxB - 1] + 1;
                } else {
                    dp[idxA][idxB] = Math.max(dp[idxA - 1][idxB], dp[idxA][idxB - 1]);
                }
            }
        }

        System.out.println(dp[lengthA][lengthB]);
    }
}
