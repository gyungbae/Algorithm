import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] channels = new String[n];

        int kbs1 = -1;
        int kbs2 = -1;

        for (int idx = 0; idx < n; idx++) {
            channels[idx] = br.readLine();

            if (channels[idx].equals("KBS1"))
                kbs1 = idx;

            if (channels[idx].equals("KBS2"))
                kbs2 = idx;
        }

        StringBuilder sb = new StringBuilder();

        for (int idx = 0; idx < kbs1; idx++)
            sb.append('1');

        for (int idx = 0; idx < kbs1; idx++)
            sb.append('4');

        if (kbs1 > kbs2)
            kbs2++;

        for (int idx = 0; idx < kbs2; idx++)
            sb.append('1');

        for (int idx = 0; idx < kbs2 - 1; idx++)
            sb.append('4');

        System.out.println(sb);
    }
}
