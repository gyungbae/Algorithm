import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = br.readLine();

            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 0 && b == 0 && c == 0) 
                break;

            int[] arr = {a, b, c};
            Arrays.sort(arr); 

            if (arr[2] >= arr[0] + arr[1]) {
                sb.append("Invalid\n");
            } else if (arr[0] == arr[1] && arr[1] == arr[2]) {
                sb.append("Equilateral\n");
            } else if (arr[0] == arr[1] || arr[1] == arr[2] || arr[0] == arr[2]) {
                sb.append("Isosceles\n");
            } else {
                sb.append("Scalene\n");
            }
        }

        System.out.print(sb.toString());
    }
}
