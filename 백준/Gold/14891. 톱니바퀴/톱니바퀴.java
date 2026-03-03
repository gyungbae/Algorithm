import java.io.*;
import java.util.*;

public class Main {
    static Gear[] gears = new Gear[5];
    static int k;

    static boolean[] turned = new boolean[5];
    static final int LEFT = 6;
    static final int RIGHT = 2;

    static void turn(int gear, int direction) {
        Gear current = gears[gear];

        int left = gear - 1;
        if (left >= 1 && !turned[left] && 
                current.state[(current.stdIdx + LEFT) % 8] != gears[left].state[(gears[left].stdIdx + RIGHT) % 8]) {
            turned[left] = true;
            turn(left, direction * -1);
        }

        int right = gear + 1;
        if (right <= 4 && !turned[right] && 
                current.state[(current.stdIdx + RIGHT) % 8] != gears[right].state[(gears[right].stdIdx + LEFT) % 8]) {
            turned[right] = true;
            turn(right, direction * -1);
        }

        current.stdIdx = (current.stdIdx + 8 - direction) % 8;
    }

    static int getScore() {
        int score = 0;

        if (gears[1].state[gears[1].stdIdx] == 1) score += 1;
        if (gears[2].state[gears[2].stdIdx] == 1) score += 2;
        if (gears[3].state[gears[3].stdIdx] == 1) score += 4;
        if (gears[4].state[gears[4].stdIdx] == 1) score += 8;

        return score;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int wheel = 1; wheel <= 4; wheel++) {
            String input = br.readLine();
            int[] arr = new int[8];
            for (int idx = 0; idx < 8; idx++) {
                arr[idx] = input.charAt(idx) - '0';
            }

            gears[wheel] = new Gear(arr);
        }

        k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gear = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            turned = new boolean[5];
            turned[gear] = true;
            turn(gear, direction);
        }

        System.out.println(getScore());
    }
}

class Gear {
    int[] state;
    int stdIdx;

    public Gear(int[] state) {
        this.state = state;
    }
}
