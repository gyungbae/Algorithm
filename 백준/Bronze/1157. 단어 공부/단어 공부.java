import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] count = new int[26];
        String input = br.readLine().toUpperCase();

        for (char ch : input.toCharArray()) {
            count[ch - 'A']++;
        }

        int max = 0;
        int maxIdx = 0;
        int maxCount = 0;

        for (int idx = 0; idx < 26; idx++) {
            if (count[idx] > max) {
                max = count[idx];
                maxIdx = idx;
                maxCount = 1;
            } else if (count[idx] == max) {
                maxCount++;
            }
        }

        System.out.println(maxCount > 1 ? "?" : (char)(maxIdx + 'A'));
    }
}