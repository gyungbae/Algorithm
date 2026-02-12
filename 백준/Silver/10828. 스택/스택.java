import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] stack = new int[n]; 
        int top = 0; 

        for (int idx = 0; idx < n; idx++) {
            String line = br.readLine();

            if (line.charAt(0) == 'p') { 
                if (line.charAt(1) == 'u') { 
                    int x = Integer.parseInt(line.substring(5));
                    stack[top++] = x;
                } else { 
                    if (top == 0) sb.append(-1).append('\n');
                    else sb.append(stack[--top]).append('\n');
                }
            } else if (line.charAt(0) == 's') { 
                sb.append(top).append('\n');
            } else if (line.charAt(0) == 'e') { 
                sb.append(top == 0 ? 1 : 0).append('\n');
            } else { 
                if (top == 0) sb.append(-1).append('\n');
                else sb.append(stack[top - 1]).append('\n');
            }
        }

        System.out.print(sb.toString());
    }
}
