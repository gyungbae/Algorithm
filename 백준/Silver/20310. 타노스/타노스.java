import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int length = input.length();

        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 0; i < length; i++) {
            if (input.charAt(i) == '0') {
                zeroCount++;
                continue;
            }

            oneCount++;
        }

        int zeroTarget = zeroCount / 2;
        int oneTarget = oneCount / 2;
        boolean[] removed = new boolean[length];
        for (int i = 0; i < length; i++) {
            if(oneTarget == 0)
                break;

            if (input.charAt(i) == '1' && !removed[i]) {
                removed[i] = true;
                oneTarget--;
            }
        }

        for (int i = length - 1; i >= 0; i--) {
            if(zeroTarget == 0)
                break;

            if (input.charAt(i) == '0' && !removed[i]) {
                removed[i] = true;
                zeroTarget--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if(!removed[i])
                sb.append(input.charAt(i));
        }

        System.out.println(sb);
    }
}
