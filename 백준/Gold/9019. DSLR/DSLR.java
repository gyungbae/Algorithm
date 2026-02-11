import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int A, B;

    static StringBuilder sb = new StringBuilder();

    static boolean[] visited;
    static int[] previousNumArr;
    static char[] commandRecordArr;
    static char[] commandArr = {'D', 'S', 'L', 'R'};

    static void BFS() {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(A);
        visited[A] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == B) {
                break;
            }

            for (char command : commandArr) {
                int next = DSLR(current, command);
                if (!visited[next]) {
                    visited[next] = true;
                    previousNumArr[next] = current;
                    commandRecordArr[next] = command;
                    queue.offer(next);
                }
            }
        }
    }

    static int DSLR(int num, char command) {
        switch (command) {
            case 'D' -> {   return (num * 2) % 10000;   }
            case 'S' -> {   return num == 0 ? 9999 : num - 1;   }
            case 'L' -> {   return (num % 1000) * 10 + (num / 1000);    }
            default -> {   return (num % 10) * 1000 + (num / 10);  }    //R
        }
    }

    static void traceBack() {
        int current = B;
        StringBuilder result = new StringBuilder();
        while (current != A) {
            result.append(commandRecordArr[current]); 
            current = previousNumArr[current];       
        }

        sb.append(result.reverse()).append("\n");
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            commandRecordArr = new char[10000];
            previousNumArr = new int[10000];
            Arrays.fill(previousNumArr, -1);

            BFS();
            traceBack();
        }

        System.out.println(sb);
    }
}
