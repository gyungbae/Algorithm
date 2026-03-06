import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int row = 1;
        int col = 1;
        int colCount = 1;
        int rowCount = 1;

        while(true) {
            col += M + 1;
            if(col > W)
                break;

            colCount++;
        }

        while (true) {
            row += N + 1;
            if (row > H)
                break;

            rowCount++;
        }

        System.out.println(colCount * rowCount);
    }
}
