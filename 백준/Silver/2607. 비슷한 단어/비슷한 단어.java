import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String standard = br.readLine();
        int stdLength = standard.length();
        int[] stdCountArr = new int[26];
        for (int i = 0; i < stdLength; i++) {
            stdCountArr[standard.charAt(i) - 'A']++;
        }

        int answer = 0;
        for (int i = 0; i < N - 1; i++) {
            String input = br.readLine();

            if(Math.abs(input.length() - stdLength) > 1)
                continue;

            int[] countArr = new int[26];
            for (int j = 0; j < input.length(); j++) {
                countArr[input.charAt(j) - 'A']++;
            }

            int plus = 0;
            int minus = 0;
            for (int k = 0; k < 26; k++) {
                if (stdCountArr[k] > countArr[k])
                    minus += stdCountArr[k] - countArr[k];
                else
                    plus += countArr[k] - stdCountArr[k];
            }

            if ((plus == 0 && minus == 0) || (plus == 1 && minus == 1) || (plus + minus == 1))
                answer++;
        }

        System.out.println(answer);
    }
}
