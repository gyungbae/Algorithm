import java.io.*;
import java.util.*;

public class Main {
    static int S, P;
    static String input;
    static int[] condition;

    static int answer;
    static int[] password;
    static int leftIdx;
    static int rightIdx;

    static void add(char ch) {
        switch(ch) {
            case 'A' -> password[0]++;
            case 'C' -> password[1]++;
            case 'G' -> password[2]++;
            default -> password[3]++;
        }
    }

    static void remove(char ch) {
        switch(ch) {
            case 'A' -> password[0]--;
            case 'C' -> password[1]--;
            case 'G' -> password[2]--;
            default -> password[3]--;
        }
    }

    static boolean isUsable() {
        for (int idx = 0; idx < 4; idx++) {
            if (condition[idx] > password[idx]) {
                return false;
            }
        }
        
        return true;
    }

    static void setWindow() {
        for (int idx = leftIdx; idx <= rightIdx; idx++) {
            char ch = input.charAt(idx);
            add(ch);
        }

        if (isUsable()) {
            answer++;
        }
    }

    static void slide() {
        while (rightIdx < S - 1) {
            rightIdx++;
            add(input.charAt(rightIdx));
            remove(input.charAt(leftIdx));
            leftIdx++;

            if (isUsable()) {
                answer++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        input = br.readLine();

        condition = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < 4; idx++) {
            condition[idx] = Integer.parseInt(st.nextToken());
        }

        leftIdx = 0;
        rightIdx = P - 1;
        answer = 0;
        password = new int[4];
        setWindow();
        slide();

        System.out.println(answer);
    }
}
