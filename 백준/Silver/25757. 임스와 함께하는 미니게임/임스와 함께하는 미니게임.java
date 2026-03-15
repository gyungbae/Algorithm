import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        String game = st.nextToken();

        Set<String> set = new HashSet<>();

        for (int person = 0; person < N; person++) {
            set.add(br.readLine());
        }

        int people = 0;

        if (game.equals("Y")) {
            people = 1;
        } else if (game.equals("F")) {
            people = 2;
        } else if (game.equals("O")) {
            people = 3;
        }

        System.out.println(set.size() / people);
    }
}
