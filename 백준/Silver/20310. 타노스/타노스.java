import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int zeroCount = 0;
        int oneCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') 
                zeroCount++;
            
            else oneCount++;
        }

        int removeZero = zeroCount / 2;
        int removeOne = oneCount / 2;

        boolean[] removed = new boolean[s.length()];

        for (int i = 0; i < s.length() && removeOne > 0; i++) {
            if (s.charAt(i) == '1') {
                removed[i] = true;
                removeOne--;
            }
        }

        for (int i = s.length() - 1; i >= 0 && removeZero > 0; i--) {
            if (s.charAt(i) == '0') {
                removed[i] = true;
                removeZero--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!removed[i]) {
                sb.append(s.charAt(i));
            }
        }

        System.out.println(sb);
    }
}
