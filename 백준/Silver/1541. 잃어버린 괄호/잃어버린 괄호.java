import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int sum = 0;
        String posNum = "";
        String negNum = "";
        boolean isNeg = false;
        for (int idx = 0; idx < input.length(); idx++) {
            char ch = input.charAt(idx);
            if ('0' <= ch && ch <= '9') {
                if (isNeg) {
                    negNum += ch;
                } else {
                    posNum += ch;
                }

                continue;
            }

            if (ch == '-') {
                if (isNeg) {
                    sum -= Integer.parseInt(negNum);
                    negNum = "";
                } else {
                    isNeg = true;
                    sum += Integer.parseInt(posNum);
                    posNum = "";
                }
            } else {
                if(isNeg) {
                    sum -= Integer.parseInt(negNum);
                    negNum = "";
                } else {
                    sum += Integer.parseInt(posNum);
                    posNum = "";
                }
            }
        }

        if (!posNum.equals("")) {
            sum += Integer.parseInt(posNum);
        }
        if (!negNum.equals("")) {
            sum -= Integer.parseInt(negNum);
        }
        System.out.println(sum);
    }
}
