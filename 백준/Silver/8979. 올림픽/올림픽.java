import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Country[] countries = new Country[N];
        Country target = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            countries[i] = new Country(num, gold, silver, bronze);

            if (num == K) {
                target = countries[i];
            }
        }

        int rank = 1;
        for (Country country : countries) {
            if (isHigher(country, target)) {
                rank++;
            }
        }

        System.out.println(rank);
    }

    static boolean isHigher(Country a, Country b) {
        if (a.gold != b.gold) {
            return a.gold > b.gold;
        }
        if (a.silver != b.silver) {
            return a.silver > b.silver;
        }
        return a.bronze > b.bronze;
    }
}

class Country {
    int num;
    int gold;
    int silver;
    int bronze;

    Country(int num, int gold, int silver, int bronze) {
        this.num = num;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }
}
