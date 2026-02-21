import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;

        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());

            if (num < 2) continue;   
            boolean isPrime = true;

            for (int divide = 2; divide <= Math.sqrt(num); divide++) {
                if (num % divide == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                count++;
            }
        }

        System.out.println(count);
    }
}