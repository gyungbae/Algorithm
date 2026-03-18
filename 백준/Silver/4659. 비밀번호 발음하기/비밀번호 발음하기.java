import java.io.*;
import java.util.*;

public class Main {
    static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();

            if(str.equals("end"))
                break;

            boolean flag1 = false;
            boolean flag2 = true;
            boolean flag3 = true;

            int count1 = 0;
            int count2 = 0;
            char prev = '-';
            for (int idx = 0; idx < str.length(); idx++) {
                char ch = str.charAt(idx);

                if (isVowel(ch)) {
                    flag1 = true;
                    count1++;
                    count2 = 0;
                } else {
                    count2++;
                    count1 = 0;
                }

                if(count1 >= 3 || count2 >= 3) {
                    flag2 = false;
                }

                if(ch == prev && (ch != 'e' && ch != 'o')) {
                    flag3 = false;
                }

                prev = ch;
            }

            if (flag1 && flag2 && flag3) {
                sb.append("<").append(str).append("> is acceptable.").append("\n");
            } else {
                sb.append("<").append(str).append("> is not acceptable.").append("\n");
            }
        }

        System.out.println(sb);
    }
}
